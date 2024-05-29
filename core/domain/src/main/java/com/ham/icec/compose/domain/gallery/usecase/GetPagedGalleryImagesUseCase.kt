package com.ham.icec.compose.domain.gallery.usecase

import com.ham.icec.compose.domain.gallery.entity.Image
import com.ham.icec.compose.domain.gallery.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedGalleryImagesUseCaseImpl @Inject constructor(
    private val galleryRepository: GalleryRepository
) : GetPagedGalleryImagesUseCase {

    override operator fun invoke(page: Int): Flow<List<Image>> =
        galleryRepository.loadLocalImages(page = page)

}

fun interface GetPagedGalleryImagesUseCase {

    operator fun invoke(page: Int): Flow<List<Image>>

}