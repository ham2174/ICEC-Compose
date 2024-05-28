package com.ham.icec.compose.domain.usecase

import com.ham.icec.compose.domain.entity.ImageInfo
import com.ham.icec.compose.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGalleryImagesUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : GetGalleryImagesUseCase {

    override suspend operator fun invoke(page: Int): Flow<List<ImageInfo>> =
        imageRepository.loadLocalImages(page = page)

}

fun interface GetGalleryImagesUseCase {

    suspend operator fun invoke(page: Int): Flow<List<ImageInfo>>

}