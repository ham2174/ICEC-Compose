package com.ham.icec.compose.mosaic.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.mosaic.EffectMode

@Composable
fun BottomFrame(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    contents: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(234.dp)
            .background(
                color = if (isSystemInDarkTheme()) {
                    IcecTheme.colors.backgroundDark
                } else {
                    IcecTheme.colors.backgroundLight
                }
            ),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        contents.invoke()
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Preview(widthDp = 360, heightDp = 800, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    IcecTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BottomFrame(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                val effectState by remember { mutableStateOf(EffectMode.MOSAIC) }

                EffectMode(
                    effectState = effectState,
                    onClickMosaic = { },
                    onClickBlur = { }
                )

                EffectSlider(
                    sliderPosition = 50f,
                    onInitEffectValue = { },
                    onEffectValueChange = { }
                )
            }
        }
    }
}