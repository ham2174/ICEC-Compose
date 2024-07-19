package com.ham.icec.compose.detect

import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage
import com.ham.icec.compose.utilandroid.extension.rotateBitmap
import com.ham.icec.compose.utilandroid.extension.toBitmap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.json.Json

const val DETECT_ROUTE = "detect?mediaStoreImage={mediaStoreImage}"
const val MEDIA_STORE_IMAGE_KEY = "mediaStoreImage"
private const val NO_MEDIA_STORE_IMAGE = "no media store image"

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.detectScreen(
    onNextStep: (String, List<BoundingBox>) -> Unit,
    onPreviousStep: () -> Unit
) {
    composable(
        route = DETECT_ROUTE,
        arguments = listOf(
            navArgument(MEDIA_STORE_IMAGE_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val context = LocalContext.current
        val mediaStoreImageJson =
            backStackEntry.arguments?.getString(MEDIA_STORE_IMAGE_KEY) ?: NO_MEDIA_STORE_IMAGE
        val mediaStoreImage = Json.decodeFromString<MediaStoreImage>(mediaStoreImageJson)
        val originalImage = mediaStoreImage.path.toUri().toBitmap(context)
            .rotateBitmap(mediaStoreImage.orientation.toFloat())

        DetectRoute(
            mediaStoreImage = mediaStoreImage,
            originalImage = originalImage,
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep
        )
    }
}
