package com.ham.icec.compose.facedetection.service

import android.graphics.Bitmap
import com.ham.icec.compose.facedetection.model.FaceDetectionResult
import kotlinx.coroutines.flow.Flow

interface DetectionService {

    fun getFastDetectFaces(bitmap: Bitmap, orientation: Long): Flow<List<FaceDetectionResult>>

    fun getAccurateDetectFaces(bitmap: Bitmap, orientation: Long): Flow<List<FaceDetectionResult>>

}