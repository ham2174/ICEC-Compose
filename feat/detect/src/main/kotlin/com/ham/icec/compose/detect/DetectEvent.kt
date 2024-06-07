package com.ham.icec.compose.detect

sealed class DetectEvent {

    data object OnClickAllSelectButton : DetectEvent()

    data class OnClickDetectedFaceImage(val detectedFaces: DetectedImage) : DetectEvent()

}