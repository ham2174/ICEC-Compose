package com.ham.icec.compose.domain.detect.model

import kotlinx.serialization.Serializable

@Serializable
data class BoundingBox(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val width: Int,
    val height: Int
)