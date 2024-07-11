package com.ham.icec.compose.result

sealed class ResultSideEffect {

    data object ShowSaveComplete : ResultSideEffect()

}