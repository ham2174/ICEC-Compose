package com.ham.icec.compose.mosaic.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
internal fun BottomContents() {

}

@Composable
internal fun EffectMode(
    effectMode: EffectMode
) {

}