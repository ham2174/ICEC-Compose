package com.ham.icec.compose.result

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.ham.icec.compose.utilandroid.extension.toBitmap

@Composable
internal fun ResultRoute(
    viewmodel: ResultViewModel = hiltViewModel(),
    image: Uri
) {

    ResultScreen(
        image = image
    )
}

@Composable
internal fun ResultScreen(
    image: Uri,
) {
    val context = LocalContext.current

    Image(
        bitmap = image.toBitmap(context).asImageBitmap(),
        contentDescription = null
    )
}