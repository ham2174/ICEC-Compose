package com.ham.icec.compose.mosaic

sealed class MosaicEvent {

    data class OnEffectValueChange(val value: Float) : MosaicEvent()

    data class OnInitEffectValue(val value: Float) : MosaicEvent()

    data object OnClickMosaic : MosaicEvent()

    data object OnClickBlur : MosaicEvent()

}