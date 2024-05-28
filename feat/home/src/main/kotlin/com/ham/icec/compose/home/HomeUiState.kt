package com.ham.icec.compose.home

sealed interface HomeUiState {
    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(val message: String) : HomeUiState
}
