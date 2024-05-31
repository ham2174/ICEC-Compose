package com.ham.icec.compose.data.detect.model

import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import com.ham.icec.compose.facedetection.model.PerformanceMode

fun DataProcessingMode.toService() : PerformanceMode =
    when(this) {
        DataProcessingMode.ACCURACY -> PerformanceMode.ACCURATE
        DataProcessingMode.SPEED -> PerformanceMode.FAST
    }