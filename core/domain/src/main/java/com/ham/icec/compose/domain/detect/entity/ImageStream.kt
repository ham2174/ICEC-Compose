package com.ham.icec.compose.domain.detect.entity

data class ImageStream(
    val stream: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageStream

        return stream.contentEquals(other.stream)
    }

    override fun hashCode(): Int {
        return stream.contentHashCode()
    }

    companion object {
        fun empty() = ImageStream(stream = ByteArray(0))
    }
}