package com.ham.icec.compose.mosaic

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.toBitmap
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.component.IcecTopBarTrailingButton
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.mosaic.component.BottomFrame
import com.ham.icec.compose.mosaic.component.EffectMode
import com.ham.icec.compose.mosaic.component.EffectSlider
import com.ham.icec.compose.mosaic.component.MosaicImage
import com.ham.icec.compose.ui.common.CenterImageFrame
import com.ham.icec.compose.ui.common.IcecTopBar

@Composable
fun MosaicRoute(
    viewModel: MosaicViewModel = hiltViewModel(),
    image: Uri,
    boundingBoxes: List<BoundingBox>,
    onNextStep: (String) -> Unit,
    onPreviousStep: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is MosaicSideEffect.NavigateToResult -> {
                    onNextStep(sideEffect.imageUriString)
                }
            }
        }
    }

    MosaicScreen(
        originalImage = image,
        boundingBoxes = boundingBoxes,
        sliderPosition = state.sliderPosition,
        effectMode = state.effectMode,
        onSaveImage = viewModel::onSaveImage,
        onPreviousStep = onPreviousStep,
        onEffectValueChange = viewModel::onEffectValueChange,
        onInitEffectValue = viewModel::onInitEffectValue,
        onClickMosaic = viewModel::onClickMosaic,
        onClickBlur = viewModel::onClickBlur,
        onChangeMosaicImage = viewModel::onChangeMosaicImage
    )
}

@Composable
private fun MosaicScreen(
    originalImage: Uri,
    boundingBoxes: List<BoundingBox>,
    sliderPosition: Float,
    effectMode: EffectMode,
    onSaveImage: () -> Unit,
    onPreviousStep: () -> Unit,
    onEffectValueChange: (Float) -> Unit,
    onInitEffectValue: () -> Unit,
    onClickMosaic: () -> Unit,
    onClickBlur: () -> Unit,
    onChangeMosaicImage: (ByteArray) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
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
                    background = IcecTheme.colors.btnBg2,
                    text = stringResource(id = R.string.save_string),
                    textColor = IcecTheme.colors.white,
                    onclick = onSaveImage
                )
            }
        )

        CenterImageFrame {
            val context = LocalContext.current
            val request = remember(originalImage) {
                ImageRequest.Builder(context)
                    .data(originalImage)
                    .crossfade(true)
                    .build()
            }

            SubcomposeAsyncImage(
                model = request,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                loading = {
                    Image(
                        painter = painterResource(R.drawable.sample_img),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                },
                error = {
                    Text(
                        text = stringResource(id = R.string.error_load_image),
                        style = IcecTheme.typography.h1,
                        color = IcecTheme.colors.white
                    )
                },
                success = { state ->
                    val imageBitmap = remember(state.result.image) {
                        runCatching { state.result.image.toBitmap().asImageBitmap() }
                            .onFailure { t -> Log.e("MosaicScreen", "Image toBitmap 실패", t) }
                            .getOrNull()
                    }

                    when (effectMode) {
                        EffectMode.MOSAIC -> {
                            imageBitmap?.let { bitmap ->
                                MosaicImage(
                                    imageBitmap = bitmap,
                                    pixelSize = sliderPosition,
                                    boundingBoxes = boundingBoxes,
                                    onChangeMosaicImage = onChangeMosaicImage
                                )
                            } ?: SubcomposeAsyncImageContent()
                        }

                        EffectMode.BLUR -> {
                            // TODO: 블러 효과 적용
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            )
        }

        BottomFrame(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            EffectMode(
                effectState = effectMode,
                onClickMosaic = onClickMosaic,
                onClickBlur = onClickBlur
            )

            EffectSlider(
                sliderPosition = sliderPosition,
                onInitEffectValue = onInitEffectValue,
                onEffectValueChange = onEffectValueChange
            )
        }
    }
}
