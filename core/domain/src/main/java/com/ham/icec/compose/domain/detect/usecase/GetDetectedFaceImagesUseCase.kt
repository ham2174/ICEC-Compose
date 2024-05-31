package com.ham.icec.compose.domain.detect.usecase

import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetectedFaceImagesUseCaseImpl @Inject constructor(
    private val detectRepository: DetectRepository
) : GetDetectedFaceImagesUseCase {

    override fun invoke(
        imagePath: String,
        mode: DataProcessingMode,
    ): Flow<List<DetectedFace>> =
        detectRepository.getDetectedFaces(imagePath = imagePath, mode = mode)

}

interface GetDetectedFaceImagesUseCase {

    operator fun invoke(
        imagePath: String,
        mode: DataProcessingMode,
    ): Flow<List<DetectedFace>>

}