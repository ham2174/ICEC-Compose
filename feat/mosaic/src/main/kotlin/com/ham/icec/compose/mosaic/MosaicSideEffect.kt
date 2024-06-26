package com.ham.icec.compose.mosaic

sealed class MosaicSideEffect {

    data class ResizedImage(val width: Int, val height: Int) : MosaicSideEffect()

}