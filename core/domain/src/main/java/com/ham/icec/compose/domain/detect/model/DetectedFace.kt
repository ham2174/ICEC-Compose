package com.ham.icec.compose.domain.detect.model

data class DetectedFace(
    val id: Int,
    val boundingBox: BoundingBox
)
