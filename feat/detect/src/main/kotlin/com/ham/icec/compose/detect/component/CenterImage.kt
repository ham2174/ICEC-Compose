package com.ham.icec.compose.detect.component

import android.graphics.Bitmap
import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.detect.extension.drawBoundingBoxesOnImage
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun CenterImage(
    image: Bitmap,
    boundingBoxes: ImmutableList<BoundingBox>
) {
    var bitmap by remember { mutableStateOf(image) }

    LaunchedEffect(boundingBoxes) {
        if (boundingBoxes.isNotEmpty()) {
            val rectangles = boundingBoxes.map { rectangle ->
                Rect(
                    rectangle.left,
                    rectangle.top,
                    rectangle.right,
                    rectangle.bottom
                )
            }
            val drewBitmap = image.drawBoundingBoxesOnImage(rectangles)
            bitmap = drewBitmap
        }
    }

    CoilImage(
        modifier = Modifier.fillMaxSize(),
        imageModel = { bitmap },
        previewPlaceholder = painterResource(id = R.drawable.sample_img),
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
        ),
        success = { state, painter ->
            Image(
                painter = painter,
                contentDescription = null
            )
        },
        failure = {
            Text(
                text = "이미지를 불러올 수 없습니다.",
                style = IcecTheme.typography.h1,
                color = IcecTheme.colors.white
            )
        }
    )
}