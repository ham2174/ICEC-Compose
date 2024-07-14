package com.ham.icec.compose.domain.detect.usecase

import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetectedFaceImagesUseCaseImpl @Inject constructor(
    private val detectRepository: DetectRepository
) : GetDetectedFaceImagesUseCase {

    override fun invoke(image: ByteArray, orientation: Long): Flow<List<DetectedFace>> =
        detectRepository.getDetectedFaces(image = image, orientation = orientation)

}

interface GetDetectedFaceImagesUseCase {

    operator fun invoke(image: ByteArray, orientation: Long): Flow<List<DetectedFace>>

}