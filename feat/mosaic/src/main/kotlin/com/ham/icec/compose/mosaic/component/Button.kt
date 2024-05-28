package com.ham.icec.compose.mosaic.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.mosaic.MosaicUiState

data class MosaicTopBarButtonState(
    val btnBg: Color,
    val btnText: String,
    val btnTextColor: Color
)

@Composable
fun MosaicTopBarButton(
    uiState: MosaicUiState,
    onclick: () -> Unit
) {
    when(uiState) {
        MosaicUiState.Step1 -> {
            MosaicTopBarButtonState(
                btnBg = IcecTheme.colors.btnBg1,
                btnText = "다음",
                btnTextColor = IcecTheme.colors.sub
            )
        }
        MosaicUiState.Step2 -> {
            MosaicTopBarButtonState(
                btnBg = IcecTheme.colors.btnBg2,
                btnText = "저장",
                btnTextColor = IcecTheme.colors.white
            )
        }
    }.let { state ->
        Box(
            modifier = Modifier
                .size(
                    width = 50.dp,
                    height = 28.dp
                )
                .background(
                    color = state.btnBg,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickableSingle { onclick() }
                .then(
                    if (isSystemInDarkTheme()) {
                        Modifier
                    } else {
                        Modifier
                            .border(
                                width = 1.dp,
                                color = IcecTheme.colors.sub,
                                shape = RoundedCornerShape(20.dp)
                            )
                    }
                )
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = state.btnText,
                style = IcecTheme.typography.sbt2,
                color = state.btnTextColor
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Step1Preview() {
    IcecTheme {
        MosaicTopBarButton(
            uiState = MosaicUiState.Step1,
            onclick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Step2Preview() {
    IcecTheme {
        MosaicTopBarButton(
            uiState = MosaicUiState.Step2,
            onclick = {}
        )
    }
}