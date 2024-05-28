package com.ham.icec.compose.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

val LocalColors = compositionLocalOf<IcecColors> {
    error("No colors provided! Make sure to wrap all usages of Icec components in IcecTheme.")
}

val LocalTypography = compositionLocalOf<IcecTypography> {
    error("No typography provided! Make sure to wrap all usages of Icec components in IcecTheme.")
}

@Composable
fun IcecTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: IcecColors = if (darkTheme) {
        IcecColors.defaultDarkColors()
    } else {
        IcecColors.defaultLightColors()
    },
    background: IcecBackground = IcecBackground.defaultBackground(darkTheme),
    typography: IcecTypography = IcecTypography.defaultTypography(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
        LocalTypography provides typography,
    ) {
        SetSystemWindowBar(background.color)
        Box(
            modifier = Modifier
                .background(background.color)
        ) {
            content()
        }
    }
}


@Composable
private fun SetSystemWindowBar(backgroundColor: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = backgroundColor.toArgb()
            window.navigationBarColor = backgroundColor.toArgb()
        }
    }
}

object IcecTheme {
    val typography: IcecTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val colors: IcecColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val background: IcecBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundTheme.current
}
