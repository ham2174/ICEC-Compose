package com.ham.icec.compose.mosaic

sealed class MosaicSideEffect {

    data class NavigateToResult(val imageUriString: String) : MosaicSideEffect()

}