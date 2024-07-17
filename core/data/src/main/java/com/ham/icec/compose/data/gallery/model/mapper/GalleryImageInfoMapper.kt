package com.ham.icec.compose.data.gallery.model.mapper

import com.ham.icec.compose.data.gallery.model.GalleryImageInfo
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage

fun GalleryImageInfo.toDomain(): MediaStoreImage =
    MediaStoreImage(
        id = id,
        path = uri.toString(),
        orientation = orientation
    )

fun List<GalleryImageInfo>.toDomain(): List<MediaStoreImage> =
    map { it.toDomain() }