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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.detect.DetectedImage
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.domain.detect.model.DetectedFace
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun BottomContents(
    detectedImages: ImmutableList<DetectedImage>,
    onClickAllSelect: () -> Unit,
    onClickImage: (DetectedImage) -> Unit,
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
            .padding(vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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

        LazyRow(
            modifier = Modifier.weight(1f),
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
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f),
            model = face,
            contentScale = ContentScale.FillBounds,
            contentDescription = null
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

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview() {
    val detectedImages = listOf(
        DetectedImage(
            face = DetectedFace(
                id = 0,
                image = ByteArray(0),
                boundingBox = BoundingBox(0, 0, 0, 0, 0, 0)
            ),
            isSelected = true
        ),
        DetectedImage(
            face = DetectedFace(
                id = 1,
                image = ByteArray(1),
                boundingBox = BoundingBox(0, 0, 0, 0, 0, 0)
            ),
            isSelected = true
        ),
        DetectedImage(
            face = DetectedFace(
                id = 2,
                image = ByteArray(2),
                boundingBox = BoundingBox(0, 0, 0, 0, 0, 0)
            ),
            isSelected = false
        ),
        DetectedImage(
            face = DetectedFace(
                id = 3,
                image = ByteArray(3),
                boundingBox = BoundingBox(0, 0, 0, 0, 0, 0)
            ),
            isSelected = false
        ),
    ).toImmutableList()

    IcecTheme {
        BottomContents(
            detectedImages = detectedImages,
            onClickAllSelect = { },
            onClickImage = { },
        )
    }
}