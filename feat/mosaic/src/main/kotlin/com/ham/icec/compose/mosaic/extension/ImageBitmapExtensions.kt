package com.ham.icec.compose.mosaic.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.ham.icec.compose.domain.detect.model.BoundingBox

internal fun ImageBitmap.applyMosaic(
    boundingBoxes: List<BoundingBox>,
    pixelSize: Int
): Bitmap {
    val mosaicBitmap = this.asAndroidBitmap().copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(mosaicBitmap)
    val paint = Paint()

    boundingBoxes.forEach { box ->
        val left = box.left
        val top = box.top
        val right = box.right
        val bottom = box.bottom

        for (x in left until right step pixelSize) {
            for (y in top until bottom step pixelSize) {
                val pixel = mosaicBitmap.getPixel(x, y)
                val rect = Rect(x, y, x + pixelSize, y + pixelSize)
                paint.color = pixel
                canvas.drawRect(rect, paint)
            }
        }
    }

    return mosaicBitmap
}