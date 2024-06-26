package com.ham.icec.compose.mosaic

sealed class MosaicEvent {

    data class OnSizedChangedImage(val width: Int, val height: Int) : MosaicEvent()

}