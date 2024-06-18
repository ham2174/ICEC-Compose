package com.ham.icec.compose.domain.detect.model

data class DetectedFace(
    val id: Int,
    val image: ByteArray,
    val boundingBox: BoundingBox
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DetectedFace

        if (id != other.id) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + image.contentHashCode()
        return result
    }
}
