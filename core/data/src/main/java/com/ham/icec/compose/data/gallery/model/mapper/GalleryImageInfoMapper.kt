package com.ham.icec.compose.data.gallery.model.mapper

import com.ham.icec.compose.domain.gallery.entity.Image
import com.ham.icec.compose.data.gallery.model.GalleryImageInfo

fun GalleryImageInfo.toDomain(): Image =
    Image(
        id = id,
        path = uri.toString(),
        orientation = orientation
    )

fun List<GalleryImageInfo>.toDomain(): List<Image> =
    map { it.toDomain() }