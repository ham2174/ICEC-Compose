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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.component.IcecLoadingIndicator
import com.ham.icec.compose.designsystem.component.IcecTopBarTrailingButton
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.detect.component.BottomContents
import com.ham.icec.compose.ui.common.CenterImageFrame
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.ui.common.IcecTopBar
import com.ham.icec.compose.utilandroid.extension.resizedByteArray
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun DetectRoute(
    viewModel: DetectViewModel = hiltViewModel(),
    imageUri: Uri,
    onNextStep: (String, List<BoundingBox>) -> Unit,
    onPreviousStep: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DetectSideEffect.ResizedImage -> {
                    if (!state.isDetected) {
                        imageUri.resizedByteArray(context, effect.width, effect.height).let {
                            viewModel.onDetectImage(it)
                        }
                    }
                }
                is DetectSideEffect.NavigateToMosaic -> {
                    onNextStep(
                        imageUri.toString(),
                        state.detectedImages
                            .filter { it.isSelected }
                            .map { it.face.boundingBox }
                    )
                }
                is DetectSideEffect.NavigateToHome -> { onPreviousStep() }
            }
        }
    }

    DetectScreen(
        centerImage = imageUri,
        detectedImages = state.detectedImages.toImmutableList(),
        boundingBoxes = state.detectedImages.map { it.face.boundingBox }.toImmutableList(),
        isLoading = state.isLoading,
        onNextStep = viewModel::onNextStep,
        onPreviousStep = viewModel::onPreviousStep,
        onClickAllSelect = viewModel::onClickAllSelectButton,
        onClickDetectedFace = viewModel::onClickDetectedFaceImage,
        onSizeChangedImage = viewModel::onSizeChangedImage
    )
}

@Composable
fun DetectScreen(
    centerImage: Uri,
    detectedImages: ImmutableList<DetectedImage>,
    boundingBoxes: ImmutableList<BoundingBox>,
    isLoading: Boolean,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onClickAllSelect: () -> Unit,
    onClickDetectedFace: (DetectedImage) -> Unit,
    onSizeChangedImage: (Int, Int) -> Unit
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
                        text = stringResource(id = R.string.next),
                        textColor = IcecTheme.colors.sub,
                        onclick = onNextStep
                    )
                }
            )

            CenterImageFrame {
                CoilImage(
                    modifier = Modifier
                        .onSizeChanged { size ->
                            if (size.height != 0 && size.width != 0) {
                                onSizeChangedImage(size.width, size.height)
                            }
                        }
                        .drawWithContent {
                            drawContent()
                            drawIntoCanvas { canvas ->
                                boundingBoxes.forEach { rect ->
                                    canvas.drawRect(
                                        left = rect.left.toFloat(),
                                        top = rect.top.toFloat(),
                                        right = rect.right.toFloat(),
                                        bottom = rect.bottom.toFloat(),
                                        paint = Paint().apply {
                                            color = Color.Red
                                            style = PaintingStyle.Stroke
                                            strokeWidth = 5f
                                        }
                                    )
                                }
                            }
                        },
                    previewPlaceholder = painterResource(id = R.drawable.sample_img),
                    imageModel = { centerImage },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit
                    ),
                )
            }

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