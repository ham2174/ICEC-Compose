package com.ham.icec.compose.data.detect.repository

import com.ham.icec.compose.data.datasource.local.DetectDataSource
import com.ham.icec.compose.data.detect.model.toDetectedFace
import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import com.ham.icec.compose.utilandroid.extension.toBitmap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetectRepositoryImpl @Inject constructor(
    private val detectDataSource: DetectDataSource
) : DetectRepository {

    override fun getDetectedFaces(image: ByteArray): Flow<List<DetectedFace>> =
        detectDataSource.getDetectedFaceBoundingBoxes(image = image).map { boundingBoxes ->
            val originalBitmapImage = image.toBitmap()
            boundingBoxes.toDetectedFace(originalImage = originalBitmapImage)
        }

}
