package com.ham.icec.compose.detect.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
internal fun ColumnScope.CenterImage(
    image: Uri
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = IcecTheme.colors.imgBg1),
        contentAlignment = Alignment.Center
    ) {
        CoilImage(
            imageModel = { image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            )
        )
    }
}