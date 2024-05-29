package com.ham.icec.compose.mosaic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MOSAIC_ROUTE = "mosaic/{image}"
const val IMAGE_KEY = "image"
private const val NO_IMAGE_STRING_URI = "no image string uri"

fun NavGraphBuilder.mosaicScreen(

) {
    composable(
        route = MOSAIC_ROUTE,
    ) {
        MosaicRoute()
    }
}
