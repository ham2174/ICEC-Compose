package com.ham.icec.compose.detect.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.detect.DetectedImage
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun BottomContents(
    detectedImages: ImmutableList<DetectedImage>,
    onClickAllSelect: () -> Unit,
    onClickImage: (DetectedImage) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(234.dp)
            .background(
                color = if (isSystemInDarkTheme()) {
                    IcecTheme.colors.backgroundDark
                } else {
                    IcecTheme.colors.backgroundLight
                }
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${detectedImages.size}명",
                style = IcecTheme.typography.sbt1,
                color = IcecTheme.colors.textColor
            )
            AllSelectButton(
                isEmptyList = detectedImages.none { image -> image.isSelected },
                onClick = onClickAllSelect
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            items(
                items = detectedImages,
                key = { detectedFace -> detectedFace.face.id }
            ) { detectedImage ->
                DetectedFaceImage(
                    face = detectedImage.face.image,
                    isSelected = detectedImage.isSelected,
                    onClickImage = { onClickImage(detectedImage) }
                )
            }
        }
    }
}

@Composable
private fun DetectedFaceImage(
    face: ByteArray,
    isSelected: Boolean,
    onClickImage: () -> Unit,
) {
    Box(
        modifier = Modifier
            .then(
                if (!isSelected) {
                    Modifier
                } else {
                    Modifier.border(
                        width = 2.dp,
                        color = IcecTheme.colors.sub,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            )
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickImage
            ),
    ) {
        CoilImage(
            modifier = Modifier
                .aspectRatio(1f),
            imageModel = { face },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds
            )
        )

        if (isSelected) {
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-4).dp, y = 4.dp),
                painter = painterResource(id = R.drawable.ic_check_14),
                contentDescription = "check",
                tint = Color.Unspecified
            )
        }
    }
}