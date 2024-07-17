package com.ham.icec.compose.data.datasource.local

import android.graphics.Bitmap
import com.ham.icec.compose.facedetection.model.FaceDetectionResult
import kotlinx.coroutines.flow.Flow

interface DetectDataSource {

    fun getFaceBoundingBoxes(
        bitmap: Bitmap,
        orientation: Long
    ): Flow<List<FaceDetectionResult>>

}
