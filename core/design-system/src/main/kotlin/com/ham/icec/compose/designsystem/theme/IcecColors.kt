package com.ham.icec.compose.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ham.icec.compose.designsystem.R

@Immutable
data class IcecColors(
    val main: Color,
    val sub: Color,
    val backgroundLight: Color,
    val backgroundDark: Color,
    val black: Color,
    val grey1: Color,
    val grey2: Color,
    val grey3: Color,
    val grey4: Color,
    val grey5: Color,
    val grey6: Color,
    val white: Color,
    val textColor: Color,
    val iconColor: Color,
    val btnBg1: Color,
    val btnBg2: Color,
    val btnBg3: Color,
    val imgBg1: Color,
) {
    companion object {
        @Composable
        fun defaultDarkColors(): IcecColors = IcecColors(
            main = colorResource(id = R.color.main),
            sub = colorResource(id = R.color.sub),
            backgroundLight = colorResource(id = R.color.background_light),
            backgroundDark = colorResource(id = R.color.background_dark),
            black = colorResource(id = R.color.black),
            grey1 = colorResource(id = R.color.grey1),
            grey2 = colorResource(id = R.color.grey2),
            grey3 = colorResource(id = R.color.grey3),
            grey4 = colorResource(id = R.color.grey4),
            grey5 = colorResource(id = R.color.grey5),
            grey6 = colorResource(id = R.color.grey6),
            white = colorResource(id = R.color.white),
            textColor = colorResource(id = R.color.white),
            iconColor = colorResource(id = R.color.white),
            btnBg1 = colorResource(id = R.color.white),
            btnBg2 = colorResource(id = R.color.sub),
            btnBg3 = colorResource(id = R.color.grey1),
            imgBg1 = colorResource(id = R.color.grey1),
        )

        @Composable
        fun defaultLightColors(): IcecColors = IcecColors(
            main = colorResource(id = R.color.main),
            sub = colorResource(id = R.color.sub),
            backgroundLight = colorResource(id = R.color.background_light),
            backgroundDark = colorResource(id = R.color.background_dark),
            black = colorResource(id = R.color.black),
            grey1 = colorResource(id = R.color.white),
            grey2 = colorResource(id = R.color.grey2),
            grey3 = colorResource(id = R.color.grey3),
            grey4 = colorResource(id = R.color.grey4),
            grey5 = colorResource(id = R.color.grey5),
            grey6 = colorResource(id = R.color.grey6),
            white = colorResource(id = R.color.white),
            textColor = colorResource(id = R.color.black),
            iconColor = colorResource(id = R.color.black),
            btnBg1 = colorResource(id = R.color.white),
            btnBg2 = colorResource(id = R.color.sub),
            btnBg3 = colorResource(id = R.color.white),
            imgBg1 = colorResource(id = R.color.grey5),
        )
    }
}