package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.model.DataProcessingMode

sealed class DetectEvent {

    data class Initial(val imagePath: String) : DetectEvent()

    data object OnClickAllSelectButton : DetectEvent()

    data class OnClickDetectedFaceImage(val detectedFaces: DetectedImage) : DetectEvent()

    data class OnClickFastModeButton(val dataProcessingMode: DataProcessingMode) : DetectEvent()

    data class OnClickPerformanceModeButton(val dataProcessingMode: DataProcessingMode) : DetectEvent()

}