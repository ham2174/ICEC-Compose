package com.ham.icec.compose.data.detect.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.core.net.toUri
import com.ham.icec.compose.domain.detect.model.DetectedFace
import java.io.ByteArrayOutputStream

fun List<Bitmap>.toDomain(): List<DetectedFace> = // 도메인으로 변환
    mapIndexed { index, bitmap ->
        DetectedFace(
            id = index,
            image = bitmap.toByteArray()
        )
    }

fun Bitmap.toByteArray(): ByteArray { // 검출된 얼굴 이미지를 ByteArray로 변환
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun String.toBitmap(context: Context): Bitmap { // 원본 이미지를 Bitmap으로 변환
    val source = ImageDecoder.createSource(context.contentResolver, this.toUri())

    return ImageDecoder.decodeBitmap(source)
}