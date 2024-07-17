package com.ham.icec.compose.domain.detect.repository

import com.ham.icec.compose.domain.detect.entity.Face
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage

interface DetectRepository {

    suspend fun getDetectedFaces(
        mediaStoreImage: MediaStoreImage
    ): Result<List<Face>>

}