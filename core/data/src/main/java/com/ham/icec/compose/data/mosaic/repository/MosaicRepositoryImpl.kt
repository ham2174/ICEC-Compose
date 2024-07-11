package com.ham.icec.compose.data.mosaic.repository

import com.ham.icec.compose.data.datasource.local.MosaicDataSource
import com.ham.icec.compose.data.mosaic.model.mapper.toDomain
import com.ham.icec.compose.domain.mosaic.entity.ImageFile
import com.ham.icec.compose.domain.mosaic.respotitory.MosaicRepository
import javax.inject.Inject

class MosaicRepositoryImpl @Inject constructor(
    private val mosaicDataSource: MosaicDataSource
) : MosaicRepository {

    override suspend fun saveEffectedImage(image: ByteArray): Result<ImageFile> =
        mosaicDataSource.saveImage(image).mapCatching { uri ->
            uri.toDomain()
        }

}