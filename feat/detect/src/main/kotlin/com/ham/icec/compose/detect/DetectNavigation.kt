package com.ham.icec.compose.detect

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder

const val DETECT_ROUTE = "detect/{image}"
const val IMAGE_KEY = "image"
private const val NO_IMAGE_STRING_URI = "no image string uri"

fun NavGraphBuilder.detectScreen(
    onNextStep: () -> Unit,
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
        val decodingUri = URLDecoder.decode(imageStringUri, "UTF-8")
        val viewModel: DetectViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.setCenterImageUri(decodingUri)
        }

        DetectRoute(
            viewModel = viewModel,
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep
        )
    }
}