package com.ham.icec.compose.data.datasource.local

import android.graphics.Rect
import com.ham.icec.compose.domain.detect.model.DataProcessingMode
import kotlinx.coroutines.flow.Flow

interface DetectDataSource {

    fun getDetectedFaceBoundingBoxes(
        imagePath: String,
        mode: DataProcessingMode
    ): Flow<List<Rect>>

}