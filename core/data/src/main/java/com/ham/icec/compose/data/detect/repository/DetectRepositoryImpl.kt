package com.ham.icec.compose.data.detect.repository

import com.ham.icec.compose.data.datasource.local.DetectDataSource
import com.ham.icec.compose.data.detect.model.toDomain
import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetectRepositoryImpl @Inject constructor(
    private val detectDataSource: DetectDataSource
) : DetectRepository {

    override fun getDetectedFaces(
        imagePath: String,
        mode: DataProcessingMode
    ): Flow<List<DetectedFace>> =
        detectDataSource.getDetectedFaces(
            imagePath = imagePath,
            mode = mode
        ).map { rectList ->
            rectList.toDomain()
        }

}