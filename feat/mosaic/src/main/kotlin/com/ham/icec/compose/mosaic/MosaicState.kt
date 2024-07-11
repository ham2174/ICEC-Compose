package com.ham.icec.compose.mosaic

data class MosaicState(
    val effectedImage: ByteArray = byteArrayOf(),
    val sliderPosition: Float = 20f,
    val effectMode: EffectMode = EffectMode.MOSAIC,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MosaicState

        if (!effectedImage.contentEquals(other.effectedImage)) return false
        if (sliderPosition != other.sliderPosition) return false
        if (effectMode != other.effectMode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = effectedImage.contentHashCode()
        result = 31 * result + sliderPosition.hashCode()
        result = 31 * result + effectMode.hashCode()
        return result
    }
}

enum class EffectMode {
    MOSAIC,
    BLUR
}