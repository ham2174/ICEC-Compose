package com.ham.icec.compose.mosaic.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.mosaic.extension.applyMosaic
import com.ham.icec.compose.utilandroid.extension.toByteArray

@Composable
internal fun MosaicImage(
    imageBitmap: ImageBitmap,
    pixelSize: Float,
    boundingBoxes: List<BoundingBox>,
    onChangeMosaicImage: (ByteArray) -> Unit
) {
    var bitmapPainter by remember { mutableStateOf<BitmapPainter?>(null) }

    LaunchedEffect(pixelSize) {
        val mosaicImage = imageBitmap.applyMosaic(
            boundingBoxes,
            pixelSize.toInt()
        ).asImageBitmap()
        bitmapPainter = BitmapPainter(mosaicImage)
        onChangeMosaicImage(mosaicImage.asAndroidBitmap().toByteArray())
    }

    bitmapPainter?.let { painter ->
        Image(
            painter = painter,
            contentDescription = null,
        )
    }
}