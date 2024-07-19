package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.entity.BoundingBox

sealed class DetectSideEffect {

    data object NavigateToHome : DetectSideEffect()

    data object NavigateToMosaic : DetectSideEffect()

    data class DrawBoundingBoxesOnMediaStoreImage(val rectangles: List<BoundingBox>) : DetectSideEffect()

}