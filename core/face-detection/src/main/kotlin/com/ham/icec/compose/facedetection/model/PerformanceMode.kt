package com.ham.icec.compose.facedetection.model

import com.google.mlkit.vision.face.FaceDetectorOptions

enum class PerformanceMode(val type: Int) {
    FAST(FaceDetectorOptions.PERFORMANCE_MODE_FAST),
    ACCURATE(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE),
    ;
}