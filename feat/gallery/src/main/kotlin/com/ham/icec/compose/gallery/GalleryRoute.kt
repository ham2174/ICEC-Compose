package com.ham.icec.compose.gallery

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.modifier.clickableSingleNoRipple
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import com.ham.icec.compose.gallery.component.GalleryContents
import com.ham.icec.compose.ui.common.IcecTopBar
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToMosaic: (MediaStoreImage) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GalleryScreen(
        galleryImages = uiState.galleryImages.toImmutableList(),
        isLastPage = uiState.isLastPage,
        onNextPageImages = viewModel::fetchNextGalleryList,
        onPrevious = onNavigateToHome,
        onClickPhoto = onNavigateToMosaic
    )
}

private const val THRESHOLD = 25

@Composable
internal fun GalleryScreen(
    galleryImages: ImmutableList<MediaStoreImage>,
    isLastPage: Boolean,
    onNextPageImages: () -> Unit,
    onPrevious: () -> Unit,
    onClickPhoto: (MediaStoreImage) -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    val derivedStateOf =
        remember(galleryImages) { derivedStateOf { lazyGridState.firstVisibleItemIndex >= galleryImages.size - THRESHOLD } }

    LaunchedEffect(derivedStateOf.value) {
        if (derivedStateOf.value && isLastPage.not() && galleryImages.isNotEmpty()) {
            onNextPageImages()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        IcecTopBar(
            leadingContent = {
                Icon(
                    modifier = Modifier.clickableSingleNoRipple(onClick = onPrevious),
                    painter = painterResource(id = R.drawable.ic_close_32),
                    contentDescription = "Close",
                    tint = IcecTheme.colors.iconColor
                )
            },
            centerContent = {
                Text(
                    text = "갤러리",
                    style = IcecTheme.typography.t1,
                    color = IcecTheme.colors.textColor
                )
            },
        )

        if (galleryImages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "이미지가 없습니다.",
                    style = IcecTheme.typography.h1,
                    color = IcecTheme.colors.textColor
                )
            }
        } else {
            GalleryContents(
                lazyGridState = lazyGridState,
                photos = galleryImages,
                onClickPhoto = onClickPhoto
            )
        }
    }
}

/*================== Previews ==================*/

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun GalleryScreenDarkPreview() {
    IcecTheme {
        GalleryScreen(
            galleryImages = List(10) {
                MediaStoreImage(it.toLong(), "", 0)
            }.toImmutableList(),
            isLastPage = false,
            onNextPageImages = {},
            onPrevious = {},
            onClickPhoto = {}
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun GalleryScreenLightPreview() {
    IcecTheme {
        GalleryScreen(
            galleryImages = List(10) {
                MediaStoreImage(it.toLong(), "", 0)
            }.toImmutableList(),
            isLastPage = false,
            onNextPageImages = {},
            onPrevious = {},
            onClickPhoto = {}
        )
    }
}
