package com.ham.icec.compose.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
fun IcecTopBarTrailingButton(
    modifier: Modifier = Modifier,
    background: Color,
    text: String,
    textColor: Color,
    onclick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(20.dp)
            )
            .clickableSingleNoRipple(onClick = onclick)
            .clip(RoundedCornerShape(20.dp))
            .padding(horizontal = 14.5f.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = IcecTheme.typography.sbt2,
            color = textColor
        )
    }
}
