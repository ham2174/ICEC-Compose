package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.model.DetectedFace

data class DetectUiState(
    val detectedImages: List<DetectedImage> = emptyList(),
    val isLoading: Boolean = true,
    val isDetected: Boolean = false
)

data class DetectedImage(
    val face: DetectedFace,
    val isSelected: Boolean
)
