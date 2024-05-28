package com.ham.icec.compose.mosaic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MosaicViewModel @Inject constructor(

): ViewModel() {

    private val _uiState: MutableStateFlow<MosaicUiState> = MutableStateFlow(MosaicUiState.Step1)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<MosaicEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _uiModel: MutableStateFlow<MosaicUiModel> = MutableStateFlow(MosaicUiModel())
    val uiModel = _uiModel.asStateFlow()

    fun nextStep() {
        viewModelScope.launch {
            when (_uiState.value) {
                MosaicUiState.Step1 -> _uiState.value = MosaicUiState.Step2
                MosaicUiState.Step2 -> _event.emit(MosaicEvent.SaveMosaicImage)
            }
        }
    }

    fun previousStep() {
        viewModelScope.launch {
            when (_uiState.value) {
                MosaicUiState.Step1 -> _event.emit(MosaicEvent.NavigateToHome)
                MosaicUiState.Step2 -> _uiState.value = MosaicUiState.Step1
            }
        }
    }

    fun setMosaicImageUri(uri: String) {
        _uiModel.value = _uiModel.value.copy(centerImage = uri)
    }

}