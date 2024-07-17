package com.ham.icec.compose.utilandroid.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

fun Uri.resizedByteArray(
    context: Context,
    width: Int,
    height: Int
): ByteArray {
    val estimatedSize = width * height * 4 // ARGB_8888의 경우 4바이트가 필요
    return ByteArrayOutputStream(estimatedSize).use { outputStream ->
        this.toBitmap(context)
            .resizedBitmap(width, height)
            .apply { compress(Bitmap.CompressFormat.JPEG, 100, outputStream) }
        outputStream.toByteArray()
    }
}
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

fun Uri.toBitmap(context: Context): Bitmap =
    context.contentResolver.openInputStream(this)?.use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    } ?: throw IllegalArgumentException("uri로부터 bitmap을 생성할 수 없습니다.") // TODO : Custom Exception 정의 - Uri 이미지 Bitmap으로 변환 실패

suspend fun Uri.toByteArray(context: Context): ByteArray? = withContext(Dispatchers.IO) {
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(this@toByteArray)
        inputStream?.readBytes()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}