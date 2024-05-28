package com.ham.icec.compose.data.datasource.local

import com.ham.icec.compose.data.model.GalleryImageInfo
import kotlinx.coroutines.flow.Flow

interface LocalImageDataSource {

    fun fetchGalleryImages(page: Int): Flow<List<GalleryImageInfo>>

}