package com.ham.icec.compose.detect

sealed class DetectEvent {

    data class OnSizeChangedImage(val width: Int, val height: Int) : DetectEvent()

    data class OnDetectImage(val image: ByteArray, val orientation: Long) : DetectEvent()

    data object OnClickAllSelectButton : DetectEvent()

    data class OnClickDetectedFaceImage(val detectedFaces: DetectedImage) : DetectEvent()

    data object OnNextStep : DetectEvent()

    data object OnPreviousStep : DetectEvent()

}