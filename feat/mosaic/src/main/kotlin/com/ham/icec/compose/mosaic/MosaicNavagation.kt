package com.ham.icec.compose.mosaic

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ham.icec.compose.domain.detect.entity.BoundingBox
import kotlinx.serialization.json.Json

const val MOSAIC_ROUTE = "mosaic/{imageByteArray}/{boundingBoxes}"
const val DETECTED_IMAGE_KEY = "imageByteArray"
const val BOUNDING_BOXES_KEY = "boundingBoxes"
private const val NO_IMAGE_STRING_URI = "no image string uri"
private const val NO_BOUNDING_BOXES_STRING = "no bounding boxes string"

fun NavGraphBuilder.mosaicScreen(
    onNextStep: (String) -> Unit,
    onPreviousStep: () -> Unit,
) {
    composable(
        route = MOSAIC_ROUTE,
        arguments = listOf(
            navArgument(DETECTED_IMAGE_KEY) {
                type = NavType.StringType
            },
            navArgument(BOUNDING_BOXES_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val encodeImage =
            backStackEntry.arguments?.getString(DETECTED_IMAGE_KEY) ?: NO_IMAGE_STRING_URI
        val encodeBoundingBoxes =
            backStackEntry.arguments?.getString(BOUNDING_BOXES_KEY) ?: NO_BOUNDING_BOXES_STRING

        val uriImage = encodeImage.toUri()
        val boundingBoxes = Json.decodeFromString<List<BoundingBox>>(encodeBoundingBoxes)

        MosaicRoute(
            image = uriImage,
            boundingBoxes = boundingBoxes,
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep
        )
    }
}
