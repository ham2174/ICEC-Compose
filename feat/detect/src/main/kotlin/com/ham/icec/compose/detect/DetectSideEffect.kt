package com.ham.icec.compose.detect

sealed class DetectSideEffect {

    data class ResizedImage(val width: Int, val height: Int) : DetectSideEffect()

}