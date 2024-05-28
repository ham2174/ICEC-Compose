package com.ham.icec.compose.domain.repository

import com.ham.icec.compose.domain.entity.ImageInfo
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun loadLocalImages(page: Int): Flow<List<ImageInfo>>

}