package com.ham.icec.compose.mosaic.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EffectSlider(
    sliderPosition: Float,
    onInitEffectValue: () -> Unit,
    onEffectValueChange: (Float) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.effect_strength_string),
                style = IcecTheme.typography.sbt1,
                color = IcecTheme.colors.textColor
            )

            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onInitEffectValue),
                painter = if (isSystemInDarkTheme()) {
                    painterResource(id = R.drawable.ic_refresh_dark_25)
                } else {
                    painterResource(id = R.drawable.ic_refresh_light_25)
                },
                contentDescription = "default",
                tint = Color.Unspecified
            )
        }

        Slider(
            value = sliderPosition,
            valueRange = 1f..40f,
            onValueChange = onEffectValueChange,
            steps = 40,
            thumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = IcecTheme.colors.grey6,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    interactionSource = remember { MutableInteractionSource() },
                    thumbSize = DpSize(16.dp, 16.dp),
                    colors = IcecTheme.colors.sliderColors
                )
            },
            track = { sliderState ->
                SliderDefaults.Track(
                    sliderState = sliderState,
                    colors = IcecTheme.colors.sliderColors,
                )
            },
        )
    }
}