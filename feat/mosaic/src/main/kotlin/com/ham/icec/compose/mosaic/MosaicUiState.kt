package com.ham.icec.compose.mosaic

sealed class MosaicUiState {

    data object Step1 : MosaicUiState()

    data object Step2 : MosaicUiState()
}

sealed interface MosaicEvent {

    data object SaveMosaicImage : MosaicEvent

    data object NavigateToHome : MosaicEvent

}

data class MosaicUiModel(
    val centerImage: String = ""
)