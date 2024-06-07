package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.model.DetectedFace

data class DetectUiState(
    val centerImage: String = "",
    val detectedImages: List<DetectedImage> = emptyList(),
    val selectedImages: List<DetectedFace> = emptyList(),
    val isLoading: Boolean = true,
)

data class DetectedImage(
    val face: DetectedFace,
    val isSelected: Boolean
)