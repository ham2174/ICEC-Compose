package com.ham.icec.compose.mosaic.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.ham.icec.compose.domain.detect.entity.BoundingBox

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
                val pixel = mosaicBitmap.getPixel(x, y) // 지정된 좌표의 픽셀 색상 가져오기
                val rect = Rect(x, y, x + pixelSize, y + pixelSize) // 사각형 영역 생성
                paint.color = pixel // 페인트 색상 설정
                canvas.drawRect(rect, paint) // 사각형 영역에 색상 채우기
            }
        }
    }

    return mosaicBitmap
}