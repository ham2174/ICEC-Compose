package com.ham.icec.compose.data.detect.repository

import android.content.Context
import com.ham.icec.compose.data.datasource.local.DetectDataSource
import com.ham.icec.compose.data.detect.model.toBitmap
import com.ham.icec.compose.data.detect.model.toFaces
import com.ham.icec.compose.domain.detect.entity.Face
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import com.ham.icec.compose.utilandroid.extension.rotateBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.single
import javax.inject.Inject

class DetectRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val detectDataSource: DetectDataSource
) : DetectRepository {

    override suspend fun getDetectedFaces(
        mediaStoreImage: MediaStoreImage
    ): Result<List<Face>> = runCatching {
        detectDataSource.getFaceBoundingBoxes(
            bitmap = mediaStoreImage.path.toBitmap(context),
            orientation = mediaStoreImage.orientation
        ).single()
    }.mapCatching { result ->
        result.toFaces(
            originalImage = mediaStoreImage.path.toBitmap(context).rotateBitmap(
                degrees = mediaStoreImage.orientation.toFloat()
            )
        )
    }

}