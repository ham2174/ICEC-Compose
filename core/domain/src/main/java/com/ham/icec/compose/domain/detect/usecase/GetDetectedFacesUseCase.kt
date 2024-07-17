package com.ham.icec.compose.domain.detect.usecase

import com.ham.icec.compose.domain.detect.entity.Face
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import javax.inject.Inject

class GetDetectedFacesUseCaseImpl @Inject constructor(
    private val detectRepository: DetectRepository
): GetDetectedFacesUseCase {

    override suspend fun invoke(mediaStoreImage: MediaStoreImage): Result<List<Face>> =
        detectRepository.getDetectedFaces(mediaStoreImage = mediaStoreImage)

}

interface GetDetectedFacesUseCase {

    suspend operator fun invoke(mediaStoreImage: MediaStoreImage): Result<List<Face>>

}