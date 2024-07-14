package com.ham.icec.compose.data.gallery.model

import android.net.Uri

data class GalleryImageInfo(
    val id: Long = 0,
    val uri: Uri = Uri.EMPTY,
    val orientation: Long = 0
)