package com.ham.icec.compose.mosaic

data class MosaicState(
    val isResized: Boolean = false,
    val effectMode: EffectMode = EffectMode.MOSAIC,
)

enum class EffectMode {
    MOSAIC,
    BLUR
}