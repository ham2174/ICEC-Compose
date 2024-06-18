package com.ham.icec.compose.domain.detect.repository

import com.ham.icec.compose.domain.detect.model.DetectedFace
import kotlinx.coroutines.flow.Flow

interface DetectRepository {

    fun getDetectedFaces(image: ByteArray): Flow<List<DetectedFace>>

}