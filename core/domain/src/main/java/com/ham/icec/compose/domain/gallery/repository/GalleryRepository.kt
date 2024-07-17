package com.ham.icec.compose.domain.gallery.repository

import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    fun loadLocalImages(page: Int): Flow<List<MediaStoreImage>>

}