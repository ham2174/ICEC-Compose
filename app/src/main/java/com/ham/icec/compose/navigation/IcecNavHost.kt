package com.ham.icec.compose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
                onClickCamera = { },
                onClickGallery = ::onNavigateToGallery
            )
            galleryScreen(
                onNavigateToHome = ::onNavigateToHome,
                onNavigateToMosaic = ::onNavigateToMosaic
            )
            mosaicScreen(
                onNavigateToHome = ::onNavigateToHome,
                onNavigateToResult = { Log.d("IcecNavHost", "onNavigateToResult: $it") }
            )
        }
    }
}

fun NavController.onNavigateToGallery() = navigate(GALLERY_ROUTE)

fun NavController.onNavigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(graph.id)
}

fun NavController.onNavigateToMosaic(imageStringUri: String) {
    val encodingUri = URLEncoder.encode(imageStringUri, "UTF-8")
    navigate(MOSAIC_ROUTE.replace("{$IMAGE_KEY}", encodingUri)) {
        popUpTo(GALLERY_ROUTE) { inclusive = true }
    }
}