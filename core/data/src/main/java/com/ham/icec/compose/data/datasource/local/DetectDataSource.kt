package com.ham.icec.compose.data.datasource.local

import android.graphics.Rect
import kotlinx.coroutines.flow.Flow

interface DetectDataSource {

    fun getDetectedFaceBoundingBoxes(imagePath: String): Flow<List<Rect>>

}