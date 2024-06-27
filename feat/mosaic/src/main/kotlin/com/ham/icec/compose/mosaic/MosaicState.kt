package com.ham.icec.compose.mosaic

data class MosaicState(
    val sliderPosition: Float = 20f,
    val effectMode: EffectMode = EffectMode.MOSAIC,
)

enum class EffectMode {
    MOSAIC,
    BLUR
}