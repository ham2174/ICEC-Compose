package com.ham.icec.compose.mosaic

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.mosaic.component.MosaicTopBarButton
import com.ham.icec.compose.ui.common.IcecTopBar
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MosaicRoute(
    viewModel: MosaicViewModel = hiltViewModel(),
    imageStringUri: String,
    onNavigateToHome: () -> Unit,
    onNavigateToResult: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setMosaicImageUri(imageStringUri)
        viewModel.event.collect { event ->
            when (event) {
                is MosaicEvent.NavigateToHome -> onNavigateToHome()
                is MosaicEvent.SaveMosaicImage -> onNavigateToResult("최종 이미지 uri 넘기기") // TODO
            }
        }
    }

    MosaicScreen(
        uiState = uiState,
        imageUri = uiModel.centerImage.toUri(),
        onNextStep = viewModel::nextStep,
        onPreviousStep = viewModel::previousStep
    )
}

@Composable
fun MosaicScreen(
    uiState: MosaicUiState,
    imageUri: Uri,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        IcecTopBar(
            leadingContent = {
                Icon(
                    modifier = Modifier.clickableSingle {
                        println(uiState)
                        onPreviousStep()
                    },
                    painter = painterResource(id = R.drawable.ic_arrow_left_32),
                    contentDescription = "Back",
                    tint = IcecTheme.colors.iconColor
                )
            },
            trailingContent = {
                MosaicTopBarButton(
                    uiState = uiState,
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
            GlideImage(
                imageModel = { imageUri },
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

        }
    }
}