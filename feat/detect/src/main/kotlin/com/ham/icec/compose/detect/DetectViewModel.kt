package com.ham.icec.compose.detect

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFacesUseCase
import com.ham.icec.compose.domain.edit.image.usecase.DrawBoundingBoxesOnMediaStoreImageUseCase
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetectViewModel @Inject constructor(
    private val getDetectedFacesUseCase: GetDetectedFacesUseCase,
    private val drawBoundingBoxesOnMediaStoreImageUseCase: DrawBoundingBoxesOnMediaStoreImageUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetectUiState> = MutableStateFlow(DetectUiState())
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<DetectEvent> = MutableSharedFlow()

    private val _sideEffect: MutableSharedFlow<DetectSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    private val mediaStoreImage: MediaStoreImage =
        checkNotNull(savedStateHandle.get<String>(MEDIA_STORE_IMAGE_KEY)?.let { jsonString ->
            Json.decodeFromString<MediaStoreImage>(jsonString)
        })

    init {
        viewModelScope.launch {
            detectImage().join()
            drawBoundingBoxesOnImage().join()

            _event.collect { event ->
                eventHandler(event)
            }
        }
    }

    fun onClickAllSelectButton() {
        viewModelScope.launch {
            _event.emit(DetectEvent.OnClickAllSelectButton)
        }
    }

    fun onClickDetectedFaceImage(detectedImage: DetectedFaceState) {
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
        viewModelScope.launch {
            when (event) {
                is DetectEvent.OnNextStep -> _sideEffect.emit(DetectSideEffect.NavigateToMosaic)
                is DetectEvent.OnPreviousStep -> _sideEffect.emit(DetectSideEffect.NavigateToHome)
                is DetectEvent.OnClickAllSelectButton -> handleSelectAll()
                is DetectEvent.OnClickDetectedFaceImage -> toggleFaceSelection(event.detectedFaces)
            }
        }
    }

    private fun handleSelectAll() {
        if (_uiState.value.detectedFaces.none { it.isSelected }) {
            _uiState.value = _uiState.value.copy(
                detectedFaces = _uiState.value.detectedFaces.map { detectedImage ->
                    detectedImage.copy(isSelected = true)
                }
            )
        } else {
            _uiState.value = _uiState.value.copy(
                detectedFaces = _uiState.value.detectedFaces.map { detectedImage ->
                    detectedImage.copy(isSelected = false)
                }
            )
        }
    }

    private fun drawBoundingBoxesOnImage(): Job =
        CoroutineScope(Dispatchers.Main).launch {
            drawBoundingBoxesOnMediaStoreImageUseCase(
                mediaStoreImage = mediaStoreImage,
                boundingBoxes = _uiState.value.detectedFaces.map { it.face.boundingBox }
            ).onSuccess { imageStream ->
                _uiState.value = _uiState.value.copy(isLoading = false)
                _sideEffect.emit(DetectSideEffect.DrawBoundingBoxesOnMediaStoreImage(imageStream))
            }.onFailure {
                Log.d("에러 발생", it.toString())
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }

    private fun detectImage(): Job =
        CoroutineScope(Dispatchers.IO).launch {
            getDetectedFacesUseCase(mediaStoreImage = mediaStoreImage)
                .onSuccess { result ->
                    _uiState.value = _uiState.value.copy(
                        detectedFaces = result.map { face -> DetectedFaceState(face = face) },
                        isDetected = true
                    )
                }.onFailure {
                    Log.d("에러 발생", it.toString())
                    _uiState.value = _uiState.value.copy(isLoading = false, isDetected = false)
                }
        }


    private fun toggleFaceSelection(detectedFaces: DetectedFaceState) {
        _uiState.value = _uiState.value.copy(
            detectedFaces = _uiState.value.detectedFaces.map { detectedImage ->
                if (detectedImage.face.id == detectedFaces.face.id) {
                    detectedImage.copy(isSelected = !detectedImage.isSelected)
                } else {
                    detectedImage
                }
            }
        )
    }

}