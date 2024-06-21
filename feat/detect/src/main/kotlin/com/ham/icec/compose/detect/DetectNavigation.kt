package com.ham.icec.compose.detect

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ham.icec.compose.domain.detect.model.BoundingBox
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val DETECT_ROUTE = "detect/{image}"
const val IMAGE_KEY = "image"
private const val NO_IMAGE_STRING_URI = "no image string uri"

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.detectScreen(
    onNextStep: (String, List<BoundingBox>) -> Unit,
    onPreviousStep: () -> Unit
) {
    composable(
        route = DETECT_ROUTE,
        arguments = listOf(
            navArgument(IMAGE_KEY) {
                type = NavType.StringType
                defaultValue = NO_IMAGE_STRING_URI
            }
        )
    ) { backStackEntry ->
        val imageStringUri = backStackEntry.arguments?.getString(IMAGE_KEY) ?: NO_IMAGE_STRING_URI
        val originalBitmapImage = imageStringUri.toUri()

        DetectRoute(
            imageUri = originalBitmapImage,
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep
        )
    }
}
