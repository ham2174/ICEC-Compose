package com.ham.icec.compose.data.model

import com.ham.icec.compose.domain.entity.ImageInfo

data class GalleryImageInfo(
    val page: Int = 0,
    val id: Long = 0,
    val name: String = "",
    val stringUri: String = ""
)

fun GalleryImageInfo.toDomain(): ImageInfo =
    ImageInfo(
        page = page,
        id = id,
        name = name,
        stringUri = stringUri
    )

fun List<GalleryImageInfo>.toDomain(): List<ImageInfo> =
    map { it.toDomain() }