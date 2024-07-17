package com.ham.icec.compose.facedetection.model

import android.graphics.Rect

data class FaceDetectionResult(
    val id: Int = 0,
    val boundingBox: Rect = Rect(),
)
