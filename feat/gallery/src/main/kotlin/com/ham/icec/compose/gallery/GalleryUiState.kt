package com.ham.icec.compose.gallery

import androidx.compose.runtime.Stable

@Stable
data class GalleryUiState(
    val galleryImages: List<ContentImage> = emptyList(),
    val isLastPage: Boolean = false
)

@Stable
data class ContentImage(
    val id: Long,
    val stringUri: String,
    val orientation: Long
)