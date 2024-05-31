package com.ham.icec.compose.data.datasource.local

import android.graphics.Rect
import com.ham.icec.compose.data.detect.model.toService
import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.facedetection.service.DetectionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetectDataSourceImpl @Inject constructor(
    private val faceDetector: DetectionService
) : DetectDataSource {

    override fun getDetectedFaces(
        imagePath: String,
        mode: DataProcessingMode
    ): Flow<List<Rect>> =
        flow {
            val result = faceDetector.detectFaces(
                image = imagePath,
                performanceMode = mode.toService())
            emit(result)
        }.flowOn(Dispatchers.IO)

}