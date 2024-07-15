package com.ham.icec.compose.detect

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ham.icec.compose.domain.detect.model.BoundingBox
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val DETECT_ROUTE = "detect/{image}/{orientation}"
const val IMAGE_KEY = "image"
const val ORIENTATION_KEY = "orientation"
private const val NO_IMAGE_STRING_URI = "no image string uri"
private const val ORIENTATION_ERROR = "orientation error"

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
            },
            navArgument(ORIENTATION_KEY) {
                type = NavType.StringType
                defaultValue = ORIENTATION_ERROR
            }
        )
    ) { backStackEntry ->
        val imageStringUri = backStackEntry.arguments?.getString(IMAGE_KEY) ?: NO_IMAGE_STRING_URI
        val orientation = backStackEntry.arguments?.getString(ORIENTATION_KEY) ?: ORIENTATION_ERROR
        val originalBitmapImage = imageStringUri.toUri()

        DetectRoute(
            imageUri = originalBitmapImage,
            orientation = orientation.toLong(),
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep
        )
    }
}
