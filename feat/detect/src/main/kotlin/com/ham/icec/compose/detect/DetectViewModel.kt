package com.ham.icec.compose.detect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetectViewModel @Inject constructor(
    private val getDetectedFaceImagesUseCase: GetDetectedFaceImagesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetectUiState> = MutableStateFlow(DetectUiState())
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<DetectEvent> = MutableSharedFlow()

    private val _sideEffect: MutableSharedFlow<DetectSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            _event.collect { event ->
                eventHandler(event)
            }
        }
    }

    fun onDetectImage(byteArray: ByteArray, orientation: Long) {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnDetectImage(byteArray, orientation))
        }
    }

    fun onSizeChangedImage(width: Int, height: Int) {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnSizeChangedImage(width, height))
        }
    }

    fun onClickAllSelectButton() {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnClickAllSelectButton)
        }
    }

    fun onClickDetectedFaceImage(detectedImage: DetectedImage) {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnClickDetectedFaceImage(detectedImage))
        }
    }

    fun onNextStep() {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnNextStep)
        }
    }

    fun onPreviousStep() {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnPreviousStep)
        }
    }

    private fun eventHandler(event: DetectEvent) {
        when (event) {
            is DetectEvent.OnNextStep -> sideEffectHandler(DetectSideEffect.NavigateToMosaic)
            is DetectEvent.OnPreviousStep -> sideEffectHandler(DetectSideEffect.NavigateToHome)
            is DetectEvent.OnSizeChangedImage -> sideEffectHandler(DetectSideEffect.ResizedImage(event.width, event.height))
            is DetectEvent.OnDetectImage -> detectImage(event.image, event.orientation)
            is DetectEvent.OnClickAllSelectButton -> handleSelectAll()
            is DetectEvent.OnClickDetectedFaceImage -> toggleFaceSelection(event.detectedFaces)

        }
    }

    private fun handleSelectAll() {
        if (_uiState.value.detectedImages.none { it.isSelected }) {
            _uiState.value = _uiState.value.copy(
                detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                    detectedImage.copy(isSelected = true)
                }
            )
        } else {
            _uiState.value = _uiState.value.copy(
                detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                    detectedImage.copy(isSelected = false)
                }
            )
        }
    }

    private fun detectImage(image: ByteArray, orientation: Long) {
        viewModelScope.launch {
            getDetectedFaceImagesUseCase(image = image, orientation = orientation)
                .catch {
                    Log.d("에러 발생", it.toString())
                    _uiState.value = _uiState.value.copy(isLoading = false, isDetected = false)
                }
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
                .collect { faces ->
                    _uiState.value = _uiState.value.copy(
                        detectedImages = faces.map { face ->
                            DetectedImage(face = face, isSelected = false)
                        },
                        isLoading = false,
                        isDetected = true
                    )
                }
        }
    }

    private fun toggleFaceSelection(detectedFaces: DetectedImage) {
        _uiState.value = _uiState.value.copy(
            detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                if (detectedImage.face.id == detectedFaces.face.id) {
                    detectedImage.copy(isSelected = !detectedImage.isSelected)
                } else {
                    detectedImage
                }
            }
        )
    }

    private fun sideEffectHandler(effect: DetectSideEffect) {
        viewModelScope.launch {
            when (effect) {
                is DetectSideEffect.ResizedImage -> _sideEffect.emit(
                    DetectSideEffect.ResizedImage(effect.width, effect.height)
                )
                is DetectSideEffect.NavigateToMosaic -> _sideEffect.emit(DetectSideEffect.NavigateToMosaic)
                is DetectSideEffect.NavigateToHome -> _sideEffect.emit(DetectSideEffect.NavigateToHome)
            }
        }
    }

}