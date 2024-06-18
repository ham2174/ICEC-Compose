package com.ham.icec.compose.data.detect.model

import android.graphics.Bitmap
import android.graphics.Rect
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.domain.detect.model.DetectedFace
import com.ham.icec.compose.utilandroid.extension.toByteArray

fun List<Rect>.toDetectedFace(originalImage: Bitmap): List<DetectedFace> =
    mapIndexed { index, rect ->
        val faceBitmap = Bitmap.createBitmap( // 얼굴 경계값 만큼 이미지 자르기
            originalImage,
            rect.left,
            rect.top,
            rect.width(),
            rect.height()
        )

        DetectedFace(
            id = index,
            image = faceBitmap.toByteArray(),
            boundingBox = BoundingBox(
                left = rect.left,
                top = rect.top,
                right = rect.right,
                bottom = rect.bottom,
                width = rect.width(),
                height = rect.height()
            )
        )
    }
