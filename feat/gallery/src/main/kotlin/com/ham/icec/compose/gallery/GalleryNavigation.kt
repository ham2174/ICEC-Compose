package com.ham.icec.compose.gallery

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage

const val GALLERY_ROUTE = "gallery"

fun NavGraphBuilder.galleryScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToMosaic: (MediaStoreImage) -> Unit
) {
    composable(route = GALLERY_ROUTE) {
        GalleryRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToMosaic = onNavigateToMosaic
        )
    }
}
