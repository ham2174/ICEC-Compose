package com.ham.icec.compose.detect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
        viewModelScope.launch {
            _event.collect { event ->
                eventHandler(event)
            }
        }
    }

    fun onSizeChangedImage(byteArray: ByteArray) {
        viewModelScope.launch {
            _event.emit(DetectEvent.Initial(byteArray))
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

    private fun detectFace() {
        viewModelScope.launch {
            getDetectedFaceImagesUseCase(image = _uiState.value.resizedImage)
                .catch {
                    Log.d("에러 발생", it.toString())
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
                .collect { faces ->
                    _uiState.value = _uiState.value.copy(
                        detectedImages = faces.map { face ->
                            DetectedImage(face = face, isSelected = false)
                        },
                        isLoading = false
                    )
                }
        }
    }

    private fun eventHandler(event: DetectEvent) {
        when (event) {
            is DetectEvent.Initial -> detectImage(event.byteArray)
            is DetectEvent.OnClickAllSelectButton -> handleSelectAll()
            is DetectEvent.OnClickDetectedFaceImage -> toggleFaceSelection(event.detectedFaces)
        }
    }

    private fun handleSelectAll() {
        if (_uiState.value.detectedImages.none { it.isSelected }) {
            _uiState.value = _uiState.value.copy(
                detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                    detectedImage.copy(isSelected = true)
                },
                selectedImages = _uiState.value.detectedImages.map { detectedImage ->
                    detectedImage.face
                }
            )
        } else {
            _uiState.value = _uiState.value.copy(
                detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                    detectedImage.copy(isSelected = false)
                },
                selectedImages = emptyList()
            )
        }
    }

    private fun detectImage(image: ByteArray) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(resizedImage = image)
            detectFace()
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

}