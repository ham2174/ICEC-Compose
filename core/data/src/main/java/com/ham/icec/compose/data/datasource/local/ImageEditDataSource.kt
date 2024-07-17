package com.ham.icec.compose.data.datasource.local

import android.graphics.Bitmap
import android.graphics.Rect

interface ImageEditDataSource {

    suspend fun drawBoundingBoxesOnImage(
        bitmap: Bitmap,
        rectangles: List<Rect>
    ): Bitmap

}