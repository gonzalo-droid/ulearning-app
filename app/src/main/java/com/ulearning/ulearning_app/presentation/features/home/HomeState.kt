package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.presentation.base.UiState

sealed class HomeState : UiState {

    object Idle : HomeState()
    object Loading : HomeState()
    data class LoginSuccess constructor(val success: Boolean) : HomeState()

}