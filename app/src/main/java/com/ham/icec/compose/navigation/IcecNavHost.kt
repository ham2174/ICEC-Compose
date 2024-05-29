package com.ham.icec.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ham.icec.compose.detect.DETECT_ROUTE
import com.ham.icec.compose.detect.detectScreen
import com.ham.icec.compose.gallery.GALLERY_ROUTE
import com.ham.icec.compose.gallery.galleryScreen
import com.ham.icec.compose.home.HOME_ROUTE
import com.ham.icec.compose.home.homeRoute
import com.ham.icec.compose.mosaic.IMAGE_KEY
import com.ham.icec.compose.mosaic.MOSAIC_ROUTE
import com.ham.icec.compose.mosaic.mosaicScreen
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
            mosaicScreen()
            detectScreen(
                onPreviousStep = ::navigateToHome,
                onNextStep = ::navigateToMosaic,
            )
        }
    }
}

fun NavController.navigateToMosaic() = navigate(MOSAIC_ROUTE) {
    restoreState = true
}

fun NavController.navigateToGallery() = navigate(GALLERY_ROUTE)

fun NavController.navigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(graph.id)
}

fun NavController.navigateToDetect(imageStringUri: String) {
    val encodingUri = URLEncoder.encode(imageStringUri, "UTF-8")
    navigate(DETECT_ROUTE.replace("{$IMAGE_KEY}", encodingUri)) {
        popUpTo(GALLERY_ROUTE) { inclusive = true }
    }
}