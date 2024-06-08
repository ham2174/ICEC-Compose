package com.ham.icec.compose.detect.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.designsystem.R

@Composable
internal fun AllSelectButton(
    isEmptyList: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isEmptyList) {
                    IcecTheme.colors.btnBg3
                } else {
                    IcecTheme.colors.sub
                },
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = IcecTheme.colors.btnStroke,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.5f.dp
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isEmptyList) {
                stringResource(id = R.string.all_select)
            } else {
                stringResource(id = R.string.all_unselect)
            },
            style = IcecTheme.typography.b2,
            color = if (isSystemInDarkTheme()) {
                IcecTheme.colors.white
            } else {
                if (isEmptyList) {
                    IcecTheme.colors.black
                } else {
                    IcecTheme.colors.white
                }
            }
        )
    }
}