package com.ham.icec.compose.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeRoute(
    onClickCamera: () -> Unit,
    onClickGallery: () -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onClickCamera = onClickCamera,
            onClickGallery = onClickGallery
        )
    }
}
