package com.ham.icec.compose.gallery

import androidx.compose.runtime.Stable
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage

@Stable
data class GalleryUiState(
    val galleryImages: List<MediaStoreImage> = emptyList(),
    val isLastPage: Boolean = false
)
