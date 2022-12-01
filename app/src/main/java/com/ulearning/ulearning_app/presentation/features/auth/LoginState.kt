package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.presentation.base.UiState

sealed class LoginState : UiState {

    object Idle : LoginState()

    object Loading : LoginState()

    data class LoginSuccess constructor(val success: Boolean) : LoginState()

}