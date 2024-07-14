package com.ham.icec.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ham.icec.compose.detect.DETECT_ROUTE
import com.ham.icec.compose.detect.IMAGE_KEY
import com.ham.icec.compose.detect.ORIENTATION_KEY
import com.ham.icec.compose.detect.detectScreen
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.gallery.GALLERY_ROUTE
import com.ham.icec.compose.gallery.galleryScreen
import com.ham.icec.compose.home.HOME_ROUTE
import com.ham.icec.compose.home.homeRoute
import com.ham.icec.compose.mosaic.BOUNDING_BOXES_KEY
import com.ham.icec.compose.mosaic.DETECTED_IMAGE_KEY
import com.ham.icec.compose.mosaic.MOSAIC_ROUTE
import com.ham.icec.compose.mosaic.mosaicScreen
import com.ham.icec.compose.result.MOSAIC_IMAGE_KEY
import com.ham.icec.compose.result.RESULT_ROUTE
import com.ham.icec.compose.result.resultScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@Composable
fun IcecNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        with(navController) {
            homeRoute(
                onClickCamera = { }, // TODO : 카메라 화면 구현
                onClickGallery = ::navigateToGallery
            )
            galleryScreen(
                onNavigateToHome = ::navigateToHome,
                onNavigateToMosaic = ::navigateToDetect
            )
            mosaicScreen(
                onPreviousStep = { popBackStack() },
                onNextStep = ::navigateToResult
            )
            detectScreen(
                onPreviousStep = ::navigateToHome,
                onNextStep = ::navigateToMosaic,
            )
            resultScreen()
        }
    }
}

fun NavController.navigateToMosaic(imageUri: String, boundingBoxes: List<BoundingBox>) {
    val encodingUri = URLEncoder.encode(imageUri, "UTF-8")
    val encodingBoundingBox = Json.encodeToString(boundingBoxes)

    navigate(
        MOSAIC_ROUTE
            .replace("{$DETECTED_IMAGE_KEY}", encodingUri)
            .replace("{$BOUNDING_BOXES_KEY}", encodingBoundingBox)
    )
}

fun NavController.navigateToGallery() = navigate(GALLERY_ROUTE)

fun NavController.navigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(graph.id)
}

fun NavController.navigateToDetect(imageStringUri: String, orientation: Long) {
    val encodingUri = URLEncoder.encode(imageStringUri, "UTF-8")

    navigate(DETECT_ROUTE
        .replace("{$IMAGE_KEY}", encodingUri)
        .replace("{$ORIENTATION_KEY}", orientation.toString())
    ) {
        popUpTo(GALLERY_ROUTE) { inclusive = true }
    }
}

fun NavController.navigateToResult(imageUri: String) {
    val encodingUri = URLEncoder.encode(imageUri, "UTF-8")
    navigate(RESULT_ROUTE.replace("{$MOSAIC_IMAGE_KEY}", encodingUri)) {
        popUpTo(DETECT_ROUTE) { inclusive = true }
    }
}