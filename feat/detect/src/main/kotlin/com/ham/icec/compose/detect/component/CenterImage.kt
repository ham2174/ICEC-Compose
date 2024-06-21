package com.ham.icec.compose.detect.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ColumnScope.CenterImage(
    image: Uri,
    boundingBoxes: ImmutableList<BoundingBox>,
    onSizeChangedImage: (Int, Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = IcecTheme.colors.imgBg1),
        contentAlignment = Alignment.Center
    ) {
        CoilImage(
            modifier = Modifier
                .onSizeChanged { size ->
                    if (size.height != 0 && size.width != 0) {
                        onSizeChangedImage(size.width, size.height)
                    }
                }
                .drawWithContent {
                    drawContent()
                    drawIntoCanvas { canvas ->
                        boundingBoxes.forEach { rect ->
                            canvas.drawRect(
                                left = rect.left.toFloat(),
                                top = rect.top.toFloat(),
                                right = rect.right.toFloat(),
                                bottom = rect.bottom.toFloat(),
                                paint = Paint().apply {
                                    color = Color.Red
                                    style = PaintingStyle.Stroke
                                    strokeWidth = 5f
                                }
                            )
                        }
                    }
                },
            previewPlaceholder = painterResource(id = R.drawable.sample_img),
            imageModel = { image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            ),
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    IcecTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CenterImage(
                image = Uri.EMPTY,
                boundingBoxes = listOf(
                    BoundingBox(
                        left = 100,
                        top = 100,
                        right = 100,
                        bottom = 100,
                        width = 200,
                        height = 100
                    )
                ).toImmutableList(),
                onSizeChangedImage = { _, _ -> }
            )
        }
    }
}
