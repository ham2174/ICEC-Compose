package com.ham.icec.compose.mosaic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.mosaic.usecase.SaveEffectedImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MosaicViewModel @Inject constructor(
    private val saveEffectedImageUseCase: SaveEffectedImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MosaicState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<MosaicEvent>()

    private val _sideEffect = MutableSharedFlow<MosaicSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            _event.collect { event ->
                eventHandler(event)
            }
        }
    }

    fun onSaveImage() {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnSaveImage)
        }
    }

    fun onChangeMosaicImage(image: ByteArray) {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnChangeMosaicImage(image))
        }
    }

    fun onEffectValueChange(value: Float) {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnEffectValueChange(value))
        }
    }

    fun onInitEffectValue() {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnInitEffectValue(DEFAULT_EFFECT_VALUE))
        }
    }

    fun onClickMosaic() {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnClickMosaic)
        }
    }

    fun onClickBlur() {
        viewModelScope.launch {
            _event.emit(MosaicEvent.OnClickBlur)
        }
    }

    private fun eventHandler(event: MosaicEvent) {
        when (event) {
            is MosaicEvent.OnEffectValueChange -> changeSliderValue(event.value)
            is MosaicEvent.OnInitEffectValue -> changeSliderValue(event.value)
            is MosaicEvent.OnClickMosaic -> changeEffectMode(EffectMode.MOSAIC)
            is MosaicEvent.OnClickBlur -> changeEffectMode(EffectMode.BLUR)
            is MosaicEvent.OnChangeMosaicImage -> changeMosaicImage(event.image)
            is MosaicEvent.OnSaveImage -> saveImage()
        }
    }

    private fun saveImage() {
        viewModelScope.launch {
            saveEffectedImageUseCase(image = _state.value.effectedImage)
                .onSuccess { imageFile ->
                    Log.d("저장됨", imageFile.toString())

                    _sideEffect.emit(MosaicSideEffect.NavigateToResult(imageFile.path))
                }
                .onFailure { throwable ->
                    Log.d("에러발생", throwable.message.toString())
                }
        }
    }

    private fun changeSliderValue(value: Float) {
        _state.value = state.value.copy(sliderPosition = value)
    }

    private fun changeEffectMode(effectMode: EffectMode) {
        _state.value = state.value.copy(effectMode = effectMode)
    }

    private fun changeMosaicImage(image: ByteArray) {
        _state.value = state.value.copy(effectedImage = image)
    }

    companion object {
        private const val DEFAULT_EFFECT_VALUE = 20f
    }

}