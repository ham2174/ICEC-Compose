package com.ham.icec.compose.domain.mosaic.respotitory

import com.ham.icec.compose.domain.mosaic.entity.ImageFile

interface MosaicRepository {

    suspend fun saveEffectedImage(image: ByteArray): Result<ImageFile>

}