package com.ham.icec.compose.data.detect.repository

import android.content.Context
import android.graphics.Bitmap
import com.ham.icec.compose.data.datasource.local.DetectDataSource
import com.ham.icec.compose.data.detect.model.toBitmap
import com.ham.icec.compose.data.detect.model.toDomain
import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetectRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val detectDataSource: DetectDataSource
) : DetectRepository {

    override fun getDetectedFaces(imagePath: String): Flow<List<DetectedFace>> =
        detectDataSource.getDetectedFaceBoundingBoxes(imagePath = imagePath).map { boundingBoxes ->
            val originalBitmapImage = imagePath.toBitmap(context)

            boundingBoxes.map { rect ->
                Bitmap.createBitmap(
                    originalBitmapImage,
                    rect.left,
                    rect.top,
                    rect.width(),
                    rect.height()
                )
            }.toDomain()
        }

}
