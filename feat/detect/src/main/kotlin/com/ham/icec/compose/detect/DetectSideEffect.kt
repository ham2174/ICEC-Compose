package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.entity.ImageStream

sealed class DetectSideEffect {

    data object NavigateToHome : DetectSideEffect()

    data object NavigateToMosaic : DetectSideEffect()

    data class DrawBoundingBoxesOnMediaStoreImage(val imageStream: ImageStream) : DetectSideEffect()

}