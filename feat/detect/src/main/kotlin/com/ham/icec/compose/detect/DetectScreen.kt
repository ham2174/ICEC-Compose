package com.ham.icec.compose.detect

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.component.IcecTopBarTrailingButton
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.ui.common.IcecTopBar
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DetectRoute(
    viewModel: DetectViewModel,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DetectScreen(
        centerImage = state.centerImage,
        onNextStep = onNextStep,
        onPreviousStep = onPreviousStep
    )
}

@Composable
fun DetectScreen(
    centerImage: String,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        IcecTopBar(
            leadingContent = {
                Icon(
                    modifier = Modifier.clickableSingleNoRipple { onPreviousStep() },
                    painter = painterResource(id = R.drawable.ic_arrow_left_32),
                    contentDescription = "Back",
                    tint = IcecTheme.colors.iconColor
                )
            },
            trailingContent = {
                IcecTopBarTrailingButton(
                    modifier = Modifier.then(
                        if (isSystemInDarkTheme()) {
                            Modifier
                        } else {
                            Modifier.border(
                                width = 1.dp,
                                color = IcecTheme.colors.sub,
                                shape = RoundedCornerShape(20.dp)
                            )
                        }
                    ),
                    background = IcecTheme.colors.btnBg1,
                    text = stringResource(id = R.string.next),
                    textColor = IcecTheme.colors.sub,
                    onclick = onNextStep
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = IcecTheme.colors.imgBg1),
            contentAlignment = Alignment.Center
        ) {
            CoilImage(
                imageModel = { centerImage.toUri() },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(234.dp)
                .background(color = IcecTheme.colors.black)
        ) {
            // TODO : 슬라이더, 검출된 얼굴 리스트 UI 구현
        }
    }
}