package com.ham.icec.compose.utilandroid.extension

import android.graphics.Bitmap
import android.graphics.Matrix
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray = // ByteArray 타입의 이미지
    ByteArrayOutputStream().use { outputStream ->
        this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.toByteArray()
    }

fun Bitmap.rotateBitmap(degrees: Float): Bitmap =
    Bitmap.createBitmap(
        this,
        0,
        0,
        this.width,
        this.height,
        Matrix().apply { postRotate(degrees) },
        true
    )