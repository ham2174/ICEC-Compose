package com.ham.icec.compose.domain.mosaic.usecase

import com.ham.icec.compose.domain.mosaic.entity.ImageFile
import com.ham.icec.compose.domain.mosaic.respotitory.MosaicRepository
import javax.inject.Inject

class SaveEffectedImageUseCaseImpl @Inject constructor(
    private val mosaicRepository: MosaicRepository
) : SaveEffectedImageUseCase {

    override suspend operator fun invoke(image: ByteArray): Result<ImageFile> =
        mosaicRepository.saveEffectedImage(image)

}

interface SaveEffectedImageUseCase {

    suspend operator fun invoke(image: ByteArray): Result<ImageFile>

}