package com.ham.icec.compose.utilandroid.extension

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.resizedBitmap(width: Int, height: Int): Bitmap =
    Bitmap.createScaledBitmap(this, width, height, true)

fun Bitmap.toByteArray(): ByteArray = // ByteArray 타입의 이미지
    ByteArrayOutputStream().use { outputStream ->
        this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.toByteArray()
    }