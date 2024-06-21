package com.ham.icec.compose.mosaic

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MosaicRoute(
    viewModel: MosaicViewModel = hiltViewModel(),
    image: Uri,
    boundingBox: List<BoundingBox>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CoilImage(
            imageModel = { image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            ),
        )
    }

}
