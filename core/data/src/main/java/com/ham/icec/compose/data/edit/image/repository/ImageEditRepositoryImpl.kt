package com.ham.icec.compose.data.edit.image.repository

import android.content.Context
import android.graphics.Rect
import androidx.core.net.toUri
import com.ham.icec.compose.data.datasource.local.ImageEditDataSource
import com.ham.icec.compose.data.edit.image.model.toImageStream
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.detect.entity.ImageStream
import com.ham.icec.compose.domain.edit.image.repository.ImageEditRepository
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import com.ham.icec.compose.utilandroid.extension.rotateBitmap
import com.ham.icec.compose.utilandroid.extension.toBitmap
import com.ham.icec.compose.utilandroid.extension.toByteArray
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageEditRepositoryImpl @Inject constructor(
    private val imageEditRepository: ImageEditDataSource,
    @ApplicationContext private val context: Context
): ImageEditRepository {

    override suspend fun drawBoundingBoxesOnMediaStoreImage(
        mediaStoreImage: MediaStoreImage,
        boundingBoxes: List<BoundingBox>
    ): Result<ImageStream> = runCatching {
        val rotatedBitmapImage = mediaStoreImage.path.toUri().toBitmap(context).rotateBitmap(mediaStoreImage.orientation.toFloat())
        val rectangles = boundingBoxes.map { boundingBox ->
            Rect(
                boundingBox.left,
                boundingBox.top,
                boundingBox.right,
                boundingBox.bottom
            )
        }

        imageEditRepository.drawBoundingBoxesOnImage(
            bitmap = rotatedBitmapImage,
            rectangles = rectangles
        )
    }.mapCatching { bitmap ->
        bitmap.toImageStream()
    }

}