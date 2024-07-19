package com.ham.icec.compose.domain.gallery.entity

import kotlinx.serialization.Serializable

@Serializable
data class MediaStoreImage(
    val id: Long = 0L,
    val path: String = "",
    val orientation: Long = 0L
)
