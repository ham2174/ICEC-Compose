package com.ham.icec.compose.data.edit.image.model

import android.graphics.Bitmap
import com.ham.icec.compose.domain.detect.entity.ImageStream
import com.ham.icec.compose.utilandroid.extension.toByteArray

fun Bitmap.toImageStream(): ImageStream =
    ImageStream(stream = this.toByteArray())