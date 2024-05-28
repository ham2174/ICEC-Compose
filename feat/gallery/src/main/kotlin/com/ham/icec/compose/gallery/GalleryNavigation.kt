package com.ham.icec.compose.gallery

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val GALLERY_ROUTE = "gallery"

fun NavGraphBuilder.galleryScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToMosaic: (String) -> Unit
) {
    composable(route = GALLERY_ROUTE) {
        GalleryRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToMosaic = onNavigateToMosaic
        )
    }
}
