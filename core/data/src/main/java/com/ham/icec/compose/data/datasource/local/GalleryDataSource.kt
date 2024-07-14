package com.ham.icec.compose.data.datasource.local

import com.ham.icec.compose.data.gallery.model.GalleryImageInfo
import kotlinx.coroutines.flow.Flow

interface GalleryDataSource {

    fun fetchMediaStoreImages(page: Int): Flow<List<GalleryImageInfo>>

}