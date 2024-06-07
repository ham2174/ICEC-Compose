package com.ham.icec.compose.detect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _event.collect { event ->
                when (event) {
                    is DetectEvent.OnClickAllSelectButton -> {
                        if (_uiState.value.detectedImages.none { it.isSelected }) { // isSelected의 값이 모두 false인 경우
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

                    is DetectEvent.OnClickDetectedFaceImage -> { // 이미지 클릭 시 isSelected 값 변경
                        _uiState.value = _uiState.value.copy(
                            detectedImages = _uiState.value.detectedImages.map { detectedImage ->
                                if (detectedImage.face.id == event.detectedFaces.face.id) {
                                    detectedImage.copy(isSelected = !detectedImage.isSelected)
                                } else {
                                    detectedImage
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    fun setCenterImage(imagePath: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(centerImage = imagePath)

            getDetectedFaceImagesUseCase(
                imagePath = imagePath,
                mode = DataProcessingMode.SPEED // TODO : 사용자가 속도 위주, 정확도 위주로 선택 하게 수정
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

    fun eventHandler(event: DetectEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

}