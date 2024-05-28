package com.ham.icec.compose.data.repository

import com.ham.icec.compose.data.datasource.local.LocalImageDataSource
import com.ham.icec.compose.data.model.toDomain
import com.ham.icec.compose.domain.entity.ImageInfo
import com.ham.icec.compose.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val localImageDataSource: LocalImageDataSource
) : ImageRepository {

    override suspend fun loadLocalImages(page: Int): Flow<List<ImageInfo>> =
        localImageDataSource.fetchGalleryImages(page = page).map {
            it.toDomain()
        }

}