package com.ham.icec.compose.result.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
internal fun BoxScope.CompletedSnackBar(
    hostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = hostState,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(
                bottom = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) { data ->
        Snackbar(
            containerColor = IcecTheme.colors.sub,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = data.visuals.message,
                color = IcecTheme.colors.white,
                style = IcecTheme.typography.sbt1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}