package com.ham.icec.compose.detect.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun Bitmap.drawBoundingBoxesOnImage(rectangles: List<Rect>): Bitmap =
    withContext(Dispatchers.IO) {
        this@drawBoundingBoxesOnImage.copy(Bitmap.Config.ARGB_8888, true).also { mutableBitmap ->
            val canvas = Canvas(mutableBitmap)
            val paint = Paint().apply {
                color = Color.RED
                style = android.graphics.Paint.Style.STROKE
                strokeWidth = 5f
            }
            rectangles.forEach { rect -> canvas.drawRect(rect, paint) }
        }
    }
