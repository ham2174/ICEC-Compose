package com.ham.icec.compose.detect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun detectFace() {
        viewModelScope.launch {
            getDetectedFaceImagesUseCase(
                imagePath = _uiState.value.centerImage,
                mode = _uiState.value.dataProcessingMode
            ).onStart {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }.collect { faces ->
                _uiState.value = _uiState.value.copy(
                    detectedImages = faces.map { face ->
                        DetectedImage(face = face, isSelected = false)
                    },
                    isLoading = false
                )
            }
        }
    }

    fun sendEvent(event: DetectEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun eventHandler(event: DetectEvent) {
        when (event) {
            is DetectEvent.Initial -> updateCenterImage(event.imagePath)
            is DetectEvent.OnClickAllSelectButton -> handleSelectAll()
            is DetectEvent.OnClickDetectedFaceImage -> toggleFaceSelection(event.detectedFaces)
            is DetectEvent.OnClickFastModeButton -> changeDataProcessingMode(event.dataProcessingMode)
            is DetectEvent.OnClickPerformanceModeButton -> changeDataProcessingMode(event.dataProcessingMode)
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

    private fun updateCenterImage(imagePath: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(centerImage = imagePath)
            detectFace()
        }
    }

    private fun changeDataProcessingMode(mode: DataProcessingMode) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(dataProcessingMode = mode)
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