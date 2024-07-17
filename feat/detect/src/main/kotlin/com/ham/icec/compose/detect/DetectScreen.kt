package com.ham.icec.compose.detect

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import com.ham.icec.compose.ui.common.CenterImageFrame
import com.ham.icec.compose.ui.common.IcecTopBar
import com.ham.icec.compose.utilandroid.extension.toByteArray
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun DetectRoute(
    viewModel: DetectViewModel = hiltViewModel(),
    mediaStoreImage: MediaStoreImage,
    onNextStep: (String, List<BoundingBox>) -> Unit,
    onPreviousStep: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var centerImage by remember { mutableStateOf(byteArrayOf(0)) }

    LaunchedEffect(Unit) {
        centerImage = mediaStoreImage.path.toUri().toByteArray(context) ?: byteArrayOf(0)

        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DetectSideEffect.NavigateToMosaic -> {
                    onNextStep(
                        mediaStoreImage.path,
                        state.detectedFaces
                            .filter { it.isSelected }
                            .map { it.face.boundingBox }
                    )
                }

                is DetectSideEffect.NavigateToHome -> {
                    onPreviousStep()
                }

                is DetectSideEffect.DrawBoundingBoxesOnMediaStoreImage -> {
                    centerImage = effect.imageStream.stream
                }
            }
        }
    }

    DetectScreen(
        centerImage = centerImage,
        detectedFaces = state.detectedFaces.toImmutableList(),
        isLoading = state.isLoading,
        onNextStep = viewModel::onNextStep,
        onPreviousStep = viewModel::onPreviousStep,
        onClickAllSelect = viewModel::onClickAllSelectButton,
        onClickDetectedFace = viewModel::onClickDetectedFaceImage,
    )
}

@Composable
fun DetectScreen(
    centerImage: ByteArray,
    detectedFaces: ImmutableList<DetectedFaceState>,
    isLoading: Boolean,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onClickAllSelect: () -> Unit,
    onClickDetectedFace: (DetectedFaceState) -> Unit,
) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IcecTopBar(
                leadingContent = {
                    Icon(
                        modifier = Modifier.clickableSingleNoRipple(onClick = onPreviousStep),
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
                        text = stringResource(id = R.string.next_string),
                        textColor = IcecTheme.colors.sub,
                        onclick = onNextStep
                    )
                }
            )

            CenterImageFrame {
                CoilImage(
                    imageModel = { centerImage },
                    previewPlaceholder = painterResource(id = R.drawable.sample_img),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit
                    ),
                    failure = {
                        Text(
                            text = "이미지를 불러올 수 없습니다.",
                            style = IcecTheme.typography.h1,
                            color = IcecTheme.colors.white
                        )
                    }
                )
            }

            BottomContents(
                detectedImages = detectedFaces,
                onClickAllSelect = onClickAllSelect,
                onClickImage = onClickDetectedFace,
            )
        }

        if (isLoading) {
            IcecLoadingIndicator()
        }
    }
}