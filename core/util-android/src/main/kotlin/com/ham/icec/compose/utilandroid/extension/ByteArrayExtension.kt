package com.ham.icec.compose.utilandroid.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun ByteArray.toBitmap(): Bitmap =
    BitmapFactory.decodeByteArray(this, 0, size)
