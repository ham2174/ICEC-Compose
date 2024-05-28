package com.ham.icec.compose.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.home.component.HomeButton

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCamera: () -> Unit,
    onClickGallery: () -> Unit
) {
    HomeContent(
        onClickGallery = onClickGallery,
        onClickCamera = onClickCamera
    )
}

@Composable
fun HomeContent(
    onClickGallery: () -> Unit,
    onClickCamera: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeButton(
                onClick = onClickGallery,
                buttonText = "Gallery",
                buttonIcon = painterResource(id = R.drawable.ic_gallery_48)
            )
            HomeButton(
                onClick = onClickCamera,
                buttonText = "Camera",
                buttonIcon = painterResource(id = R.drawable.ic_camera_48)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "최근 항목",
                style = IcecTheme.typography.t1,
                color = IcecTheme.colors.textColor,
            )
            // TODO: Back Log. 최근항목 기능과 갤러리 기능과 흡사하므로 모자이크 플로우 구현 이후에 기능 변경
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
private fun HomeScreenDarkPreview() {
    IcecTheme {
        HomeContent(
            onClickGallery = {},
            onClickCamera = {}
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun HomeScreeLightPreview() {
    IcecTheme {
        HomeContent(
            onClickGallery = {},
            onClickCamera = {}
        )
    }
}