package com.ham.icec.compose.detect

sealed class DetectEvent {

    data class Initial(val byteArray: ByteArray) : DetectEvent()

    data object OnClickAllSelectButton : DetectEvent()

    data class OnClickDetectedFaceImage(val detectedFaces: DetectedImage) : DetectEvent()

}