package com.ham.icec.compose.detect

import androidx.compose.runtime.Stable
import com.ham.icec.compose.domain.detect.entity.Face

@Stable
data class DetectUiState(
    val detectedFaces: List<DetectedFaceState> = emptyList(),
    val isLoading: Boolean = true,
    val isDetected: Boolean = false
)

@Stable
data class DetectedFaceState(
    val face: Face = Face.empty(),
    val isSelected: Boolean = false
)
