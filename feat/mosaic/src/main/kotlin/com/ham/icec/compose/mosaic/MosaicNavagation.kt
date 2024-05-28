package com.ham.icec.compose.mosaic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder

const val MOSAIC_ROUTE = "mosaic/{image}"
const val IMAGE_KEY = "image"
private const val NO_IMAGE_STRING_URI = "no image string uri"

fun NavGraphBuilder.mosaicScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToResult: (String) -> Unit
) {
    composable(
        route = MOSAIC_ROUTE,
        arguments = listOf(
            navArgument(IMAGE_KEY) {
                type = NavType.StringType
                defaultValue = NO_IMAGE_STRING_URI
            }
        )
    ) { backStackEntry ->
        val imageStringUri = backStackEntry.arguments?.getString(IMAGE_KEY) ?: NO_IMAGE_STRING_URI
        val decodingUri = URLDecoder.decode(imageStringUri, "UTF-8")
        MosaicRoute(
            onNavigateToHome = onNavigateToHome,
            imageStringUri = decodingUri,
            onNavigateToResult = onNavigateToResult
        )
    }
}
