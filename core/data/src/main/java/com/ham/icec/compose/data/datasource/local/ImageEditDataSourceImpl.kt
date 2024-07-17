package com.ham.icec.compose.data.datasource.local

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import javax.inject.Inject

class ImageEditDataSourceImpl @Inject constructor() : ImageEditDataSource {

    override suspend fun drawBoundingBoxesOnImage(
        bitmap: Bitmap,
        rectangles: List<Rect>
    ): Bitmap =
        try {
            bitmap.copy(Bitmap.Config.ARGB_8888, true).also { mutableBitmap ->
                val canvas = Canvas(mutableBitmap)
                val paint = Paint().apply {
                    color = Color.RED
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = 5f
                }
                rectangles.forEach { rect -> canvas.drawRect(rect, paint) }
            }
        } catch (e: Exception) {
            throw e
        }

}