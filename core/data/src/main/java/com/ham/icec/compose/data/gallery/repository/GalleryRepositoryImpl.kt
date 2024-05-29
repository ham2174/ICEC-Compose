package com.ham.icec.compose.data.gallery.repository

import com.ham.icec.compose.data.datasource.local.GalleryDataSource
import com.ham.icec.compose.data.gallery.model.mapper.toDomain
import com.ham.icec.compose.domain.gallery.entity.Image
import com.ham.icec.compose.domain.gallery.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val localImageDataSource: GalleryDataSource
) : GalleryRepository {

    override fun loadLocalImages(page: Int): Flow<List<Image>> =
        localImageDataSource.fetchGalleryImages(page = page).map {
            it.toDomain()
        }

}