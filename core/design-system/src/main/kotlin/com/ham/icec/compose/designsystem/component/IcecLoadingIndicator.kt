package com.ham.icec.compose.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
fun IcecLoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = IcecTheme.colors.black.copy(alpha = 0.5f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = IcecTheme.colors.sub
        )
    }
}