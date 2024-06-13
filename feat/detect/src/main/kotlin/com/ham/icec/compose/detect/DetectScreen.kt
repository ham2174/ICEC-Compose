package com.ham.icec.compose.detect

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.component.IcecLoadingIndicator
import com.ham.icec.compose.designsystem.component.IcecTopBarTrailingButton
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.detect.component.BottomContents
import com.ham.icec.compose.detect.component.CenterImage
import com.ham.icec.compose.ui.common.IcecTopBar
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun DetectRoute(
    viewModel: DetectViewModel = hiltViewModel(),
    decodingUri: String,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var hasSetImage by rememberSaveable { mutableStateOf(false) }

    if (!hasSetImage) {
        LaunchedEffect(Unit) {
            viewModel.initialCenterImage(decodingUri)
            hasSetImage = true
        }
    }

    DetectScreen(
        centerImage = state.centerImage.toUri(),
        detectedImages = state.detectedImages.toImmutableList(),
        isLoading = state.isLoading,
        onNextStep = onNextStep,
        onPreviousStep = onPreviousStep,
        onClickAllSelect = viewModel::onClickAllSelectButton,
        onClickDetectedFace = viewModel::onClickDetectedFaceImage,
    )
}

@Composable
fun DetectScreen(
    centerImage: Uri,
    detectedImages: ImmutableList<DetectedImage>,
    isLoading: Boolean,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onClickAllSelect: () -> Unit,
    onClickDetectedFace: (DetectedImage) -> Unit,
) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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

            CenterImage(image = centerImage)

            BottomContents(
                detectedImages = detectedImages,
                onClickAllSelect = onClickAllSelect,
                onClickImage = onClickDetectedFace,
            )
        }

        if (isLoading) {
            IcecLoadingIndicator()
        }
    }
}