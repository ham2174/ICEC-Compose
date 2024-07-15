package com.ham.icec.compose.facedetection.service

import android.graphics.Rect
import kotlinx.coroutines.flow.Flow

interface DetectionService {

    fun getFastDetectFaces(image: ByteArray, orientation: Long): Flow<List<Rect>>

    fun getAccurateDetectFaces(image: ByteArray, orientation: Long): Flow<List<Rect>>

}