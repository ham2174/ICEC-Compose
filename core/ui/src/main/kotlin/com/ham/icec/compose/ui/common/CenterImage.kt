package com.ham.icec.compose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.theme.IcecTheme

@Composable
fun ColumnScope.CenterImageFrame(
    centerImage: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = IcecTheme.colors.imgBg1),
        contentAlignment = Alignment.Center
    ) {
        centerImage()
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 640, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    IcecTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CenterImageFrame {
                AsyncImage(
                    model = R.drawable.sample_img,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
