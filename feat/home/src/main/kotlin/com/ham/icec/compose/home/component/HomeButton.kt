package com.ham.icec.compose.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
internal fun RowScope.HomeButton(
    onClick: () -> Unit,
    buttonText: String,
    buttonIcon: Painter,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
            .then(
                if (isSystemInDarkTheme()) {
                    Modifier.border(
                        width = 1.dp,
                        color = IcecTheme.colors.grey2,
                        shape = RoundedCornerShape(16.dp)
                    )
                } else {
                    Modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                        )
                }
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = IcecTheme.colors.grey1,
                shape = RoundedCornerShape(16.dp)
            )
            .clickableSingleNoRipple(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterVertically)
        ) {
            Icon(
                painter = buttonIcon,
                contentDescription = "gallery icon",
                tint = IcecTheme.colors.iconColor
            )
            Text(
                text = buttonText,
                style = IcecTheme.typography.h2,
                color = IcecTheme.colors.textColor
            )
        }
    }
}
