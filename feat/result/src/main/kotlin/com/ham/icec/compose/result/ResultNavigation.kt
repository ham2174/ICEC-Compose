package com.ham.icec.compose.result

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RESULT_ROUTE = "result/{uri}"
const val MOSAIC_IMAGE_KEY = "uri"
private const val NO_IMAGE_STRING_URI = "no image string uri"

fun NavGraphBuilder.resultScreen(

) {
    composable(
        route = RESULT_ROUTE,
        arguments = listOf(
            navArgument(MOSAIC_IMAGE_KEY) { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val imageUri =
            backStackEntry.arguments?.getString(MOSAIC_IMAGE_KEY) ?: NO_IMAGE_STRING_URI

        ResultRoute(
            image = imageUri.toUri()
        )
    }
}