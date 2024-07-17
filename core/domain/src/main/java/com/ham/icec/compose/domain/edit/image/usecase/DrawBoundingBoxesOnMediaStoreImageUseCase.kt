package com.ham.icec.compose.domain.edit.image.usecase

import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.detect.entity.ImageStream
import com.ham.icec.compose.domain.edit.image.repository.ImageEditRepository
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import javax.inject.Inject

class DrawBoundingBoxesOnMediaStoreImageUseCaseImpl @Inject constructor(
    private val imageEditRepository: ImageEditRepository
): DrawBoundingBoxesOnMediaStoreImageUseCase {

    override suspend fun invoke(
        mediaStoreImage: MediaStoreImage,
        boundingBoxes: List<BoundingBox>
    ): Result<ImageStream> =
        imageEditRepository.drawBoundingBoxesOnMediaStoreImage(
            mediaStoreImage = mediaStoreImage,
            boundingBoxes = boundingBoxes
        )

}

interface DrawBoundingBoxesOnMediaStoreImageUseCase {

    suspend operator fun invoke(
        mediaStoreImage: MediaStoreImage,
        boundingBoxes: List<BoundingBox>
    ): Result<ImageStream>

}