package com.ham.icec.compose.domain.gallery.repository

import com.ham.icec.compose.domain.gallery.entity.Image
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    fun loadLocalImages(page: Int): Flow<List<Image>>

}