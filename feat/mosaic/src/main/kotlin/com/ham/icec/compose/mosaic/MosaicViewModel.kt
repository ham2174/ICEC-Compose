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

    fun onSizedChangedImage(width: Int, height: Int) {
        if (!state.value.isResized) {
            viewModelScope.launch {
                _event.emit(MosaicEvent.OnSizedChangedImage(width, height))
            }
        }
    }

    private fun eventHandler(event: MosaicEvent) {
        when (event) {
            is MosaicEvent.OnSizedChangedImage -> {
                sideEffectHandler(MosaicSideEffect.ResizedImage(event.width, event.height))
            }
        }
    }

    private fun sideEffectHandler(sideEffect: MosaicSideEffect) {
        viewModelScope.launch {
            when (sideEffect) {
                is MosaicSideEffect.ResizedImage -> {
                    _sideEffect.emit(MosaicSideEffect.ResizedImage(sideEffect.width, sideEffect.height))
                    _state.value = state.value.copy(isResized = true)
                }
            }
        }
    }

}