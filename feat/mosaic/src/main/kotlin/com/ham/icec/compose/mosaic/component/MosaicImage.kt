package com.ham.icec.compose.mosaic.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.mosaic.extension.applyMosaic

@Composable
internal fun MosaicImage(
    sliderPosition: Float,
    imageBitmap: ImageBitmap,
    boundingBoxes: List<BoundingBox>
) {
    var bitmapPainter by remember { mutableStateOf<BitmapPainter?>(null) }

    LaunchedEffect(sliderPosition) {
        val mosaicImage = imageBitmap.applyMosaic(
            boundingBoxes,
            sliderPosition.toInt()
        ).asImageBitmap()
        bitmapPainter = BitmapPainter(mosaicImage)
    }

    bitmapPainter?.let { painter ->
        Image(
            painter = painter,
            contentDescription = null,
        )
    }
}