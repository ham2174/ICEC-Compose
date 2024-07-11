package com.ham.icec.compose.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(

) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<ResultSideEffect>(replay = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            _sideEffect.emit(ResultSideEffect.ShowSaveComplete)
        }
    }

    fun onShare() {

    }


}