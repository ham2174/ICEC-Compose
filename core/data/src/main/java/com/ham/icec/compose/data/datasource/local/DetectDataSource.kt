package com.ham.icec.compose.data.datasource.local

import android.graphics.Rect
import kotlinx.coroutines.flow.Flow

interface DetectDataSource {

    fun getDetectedFaceBoundingBoxes(image: ByteArray): Flow<List<Rect>>

}