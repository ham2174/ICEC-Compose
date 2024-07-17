package com.ham.icec.compose.detect

sealed class DetectEvent {

    data object OnClickAllSelectButton : DetectEvent()

    data class OnClickDetectedFaceImage(val detectedFaces: DetectedFaceState) : DetectEvent()

    data object OnNextStep : DetectEvent()

    data object OnPreviousStep : DetectEvent()

}