package com.ham.icec.compose.result

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.designsystem.theme.IcecTheme
import com.ham.icec.compose.result.component.CompletedSnackBar
import com.ham.icec.compose.ui.common.IcecTopBar
import com.ham.icec.compose.utilandroid.extension.toBitmap
import kotlinx.coroutines.launch

@Composable
internal fun ResultRoute(
    viewmodel: ResultViewModel = hiltViewModel(),
    image: Uri,
    onPrevious: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewmodel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ResultSideEffect.ShowSaveComplete -> {
                    snackBarHostState.showSnackbar(
                        message = "저장이 완료 되었습니다~!",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ResultScreen(
            image = image.toBitmap(context).asImageBitmap(),
            onPrevious = onPrevious,
            onShare = {
                coroutineScope.launch {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_STREAM, image)
                        type = "image/*"
                    }
                    val chooser = Intent.createChooser(shareIntent, "이미지 공유하기")
                    context.startActivity(chooser)
                }
            }
        )

        CompletedSnackBar(hostState = snackBarHostState)
    }
}

@Composable
internal fun ResultScreen(
    image: ImageBitmap,
    onPrevious: () -> Unit,
    onShare: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IcecTopBar(
            leadingContent = {
                Icon(
                    modifier = Modifier.clickableSingle(onClick = onPrevious),
                    painter = painterResource(id = R.drawable.ic_close_32),
                    contentDescription = "Close",
                    tint = IcecTheme.colors.iconColor
                )
            },
            centerContent = {
                Text(
                    text = "저장 완료",
                    style = IcecTheme.typography.t1,
                    color = IcecTheme.colors.textColor
                )
            },
            trailingContent = {
                Icon(
                    modifier = Modifier.clickableSingle(onClick = onShare),
                    painter = painterResource(id = R.drawable.ic_share_32),
                    contentDescription = "Share",
                    tint = IcecTheme.colors.iconColor
                )
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = IcecTheme.colors.imgBg1),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = image,
                contentDescription = null,
            )
        }
    }
}