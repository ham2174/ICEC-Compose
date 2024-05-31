package com.ham.icec.compose.facedetection.service

import android.graphics.Rect
import com.google.mlkit.vision.common.InputImage
import com.ham.icec.compose.facedetection.model.PerformanceMode

interface DetectionService {

    suspend fun detectFaces(
        image: String,
        performanceMode: PerformanceMode
    ): List<Rect>

}