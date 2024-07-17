package com.ham.icec.compose.domain.detect.entity

import kotlinx.serialization.Serializable

data class Face(
    val id: Int,
    val faceImage: ImageStream,
    val boundingBox: BoundingBox,
) {
    companion object {
        fun empty() = Face(
            id = 0,
            faceImage = ImageStream.empty(),
            boundingBox = BoundingBox.empty()
        )
    }
}

@Serializable
data class BoundingBox(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val width: Int,
    val height: Int
) {
    companion object {
        fun empty() = BoundingBox(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0,
            width = 0,
            height = 0
        )
    }
}