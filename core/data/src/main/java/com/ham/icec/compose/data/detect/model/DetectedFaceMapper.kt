package com.ham.icec.compose.data.detect.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import androidx.core.net.toUri
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.detect.entity.Face
import com.ham.icec.compose.domain.detect.entity.ImageStream
import com.ham.icec.compose.facedetection.model.FaceDetectionResult
import com.ham.icec.compose.utilandroid.extension.toBitmap
import com.ham.icec.compose.utilandroid.extension.toByteArray

fun List<FaceDetectionResult>.toFaces(originalImage: Bitmap): List<Face> =
    this.map { result ->
        Face(
            id = result.id,
            faceImage = originalImage.cropToRect(result.boundingBox).toImageStream(),
            boundingBox = result.boundingBox.toDomain()
        )
    }

fun Bitmap.cropToRect(rect: Rect): Bitmap =
    Bitmap.createBitmap(
        this,
        rect.left,
        rect.top,
        rect.width(),
        rect.height()
    )

fun Bitmap.toImageStream(): ImageStream =
    ImageStream(stream = this.toByteArray())

fun Rect.toDomain(): BoundingBox =
    BoundingBox(
        left = this.left,
        right = this.right,
        top = this.top,
        bottom = this.bottom,
        width = this.width(),
        height = this.height()
    )

fun String.toBitmap(context: Context): Bitmap =
    this.toUri().toBitmap(context)