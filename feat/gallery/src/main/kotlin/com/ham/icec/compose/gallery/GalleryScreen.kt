package com.ham.icec.compose.gallery

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.domain.entity.ImageInfo
import com.ham.icec.compose.gallery.component.GalleryContents
import com.ham.icec.compose.ui.common.IcecTopBar
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToMosaic: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GalleryScreen(
        galleryImages = uiState.galleryImages.toImmutableList(),
        onNavigateToHome = onNavigateToHome,
        fetchNextGalleryList = viewModel::fetchNextGalleryList,
        onNavigateToMosaic = onNavigateToMosaic
    )
}

@Composable
internal fun GalleryScreen(
    galleryImages: ImmutableList<ImageInfo>,
    fetchNextGalleryList: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToMosaic: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        IcecTopBar(
            leadingContent = {
                Icon(
                    modifier = Modifier.clickableSingle(
                        onClick = onNavigateToHome
                    ),
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
                galleryImages = galleryImages,
                fetchNextGalleryList = fetchNextGalleryList,
                onNavigateToMosaic = onNavigateToMosaic
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
                ImageInfo(0, it.toLong(), "", "")
            }.toImmutableList(),
            onNavigateToHome = {},
            fetchNextGalleryList = {},
            onNavigateToMosaic = {}
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
                ImageInfo(0, 0, "", "")
            }.toImmutableList(),
            onNavigateToHome = {},
            fetchNextGalleryList = {},
            onNavigateToMosaic = {}
        )
    }
}
