package com.ham.icec.compose.detect.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
internal fun ModeSelectButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .background(
                color = if (isSelected) {
                    IcecTheme.colors.sub
                } else {
                    IcecTheme.colors.btnBg3
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickableSingle(
                enabled = !isSelected,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = IcecTheme.typography.h2,
            color = if (isSelected) {
                IcecTheme.colors.white
            } else {
                IcecTheme.colors.textColor
            }
        )
    }
}