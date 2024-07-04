package com.ham.icec.compose.mosaic

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MosaicRoute(
    viewModel: MosaicViewModel = hiltViewModel(),
    image: Uri,
    boundingBoxes: List<BoundingBox>,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MosaicScreen(
        originalImage = image,
        boundingBoxes = boundingBoxes,
        sliderPosition = state.sliderPosition,
        effectMode = state.effectMode,
        onNextStep = onNextStep,
        onPreviousStep = onPreviousStep,
        onEffectValueChange = viewModel::onEffectValueChange,
        onInitEffectValue = viewModel::onInitEffectValue,
        onClickMosaic = viewModel::onClickMosaic,
        onClickBlur = viewModel::onClickBlur
    )
}

@Composable
private fun MosaicScreen(
    originalImage: Uri,
    boundingBoxes: List<BoundingBox>,
    sliderPosition: Float,
    effectMode: EffectMode,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onEffectValueChange: (Float) -> Unit,
    onInitEffectValue: () -> Unit,
    onClickMosaic: () -> Unit,
    onClickBlur: () -> Unit
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
                    onclick = onNextStep
                )
            }
        )

        CenterImageFrame {
            CoilImage(
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit
                ),
                imageModel = { originalImage },
                previewPlaceholder = painterResource(id = R.drawable.sample_img),
                success = { imageState, _ ->
                    imageState.imageBitmap?.let { imageBitmap ->
                        when (effectMode) {
                            EffectMode.BLUR -> { /* TODO : 블러 이펙트 이미지 구현 */ }
                            EffectMode.MOSAIC -> {
                                MosaicImage(
                                    imageBitmap = imageBitmap,
                                    boundingBoxes = boundingBoxes,
                                    sliderPosition = sliderPosition
                                )
                            }
                        }
                    }
                },
                failure = {
                    Text(
                        text = "이미지를 불러올 수 없습니다.",
                        style = IcecTheme.typography.h1,
                        color = IcecTheme.colors.white
                    )
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
