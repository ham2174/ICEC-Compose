package com.ham.icec.compose.mosaic.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.mosaic.EffectMode

@Composable
internal fun EffectMode(
    effectState: EffectMode,
    onClickMosaic: () -> Unit,
    onClickBlur: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MosaicButton(
            isSelected = effectState == EffectMode.MOSAIC,
            onClickMosaic = onClickMosaic
        )

        Spacer(modifier = Modifier.width(1.dp))

        BlurButton(
            isSelected = effectState == EffectMode.BLUR,
            onClickBlur = onClickBlur
        )
    }
}

@Composable
internal fun MosaicButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClickMosaic: () -> Unit,
) {
    Card(
        modifier = modifier
            .sizeIn(
                minWidth = 68.dp,
                minHeight = 68.dp,
                maxWidth = 128.dp,
                maxHeight = 128.dp
            ),
        border = if (isSelected) {
            BorderStroke(
                width = 2.dp,
                color = IcecTheme.colors.sub
            )
        } else {
            null
        },
        shape = RoundedCornerShape(8.dp),
        onClick = onClickMosaic
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_img), // TODO : 모자이크 효과 추가
            contentScale = ContentScale.Crop,
            contentDescription = "Mosaic",
        )
    }
}

@Composable
internal fun BlurButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClickBlur: () -> Unit,
) {
    Card(
        modifier = modifier
            .sizeIn(
                minWidth = 68.dp,
                minHeight = 68.dp,
                maxWidth = 128.dp,
                maxHeight = 128.dp
            ),
        border = if (isSelected) {
            BorderStroke(
                width = 2.dp,
                color = IcecTheme.colors.sub
            )
        } else {
            null
        },
        shape = RoundedCornerShape(8.dp),
        onClick = onClickBlur
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_img), // TODO : 블러 효과 추가
            contentScale = ContentScale.Crop,
            contentDescription = "Blur",
        )
    }
}