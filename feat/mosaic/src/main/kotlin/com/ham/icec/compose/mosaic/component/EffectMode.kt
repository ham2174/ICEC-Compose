package com.ham.icec.compose.mosaic.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        EffectButton(
            isSelected = effectState == EffectMode.MOSAIC,
            onClick = onClickMosaic,
            text = stringResource(id = R.string.mosaic_string)
        )

        EffectButton(
            isSelected = effectState == EffectMode.BLUR,
            onClick = onClickBlur,
            text = stringResource(id = R.string.blur_string)
        )
    }
}

@Composable
internal fun EffectButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    Box(
        modifier = modifier
            .sizeIn(
                minWidth = 100.dp,
                minHeight = 100.dp,
                maxWidth = 128.dp,
                maxHeight = 128.dp
            )
            .background(
                color = if (isSelected) {
                    IcecTheme.colors.sub
                } else {
                    IcecTheme.colors.btnBg3
                },
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = IcecTheme.colors.btnStroke,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = IcecTheme.typography.h2,
            color = if (isSystemInDarkTheme()) {
                IcecTheme.colors.white
            } else {
                if (isSelected) {
                    IcecTheme.colors.white
                } else {
                    IcecTheme.colors.black
                }
            },
        )
    }
}

@Preview(name = "31", widthDp = 360, heightDp = 800, apiLevel = 31, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "30", widthDp = 360, heightDp = 800, apiLevel = 30)
@Composable
private fun Preview() {
    IcecTheme {
        EffectMode(
            effectState = EffectMode.MOSAIC,
            onClickMosaic = { },
            onClickBlur = { }
        )
    }
}