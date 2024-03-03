package com.ulearning.ulearning_app.presentation.features.splash

import com.ulearning.ulearning_app.presentation.base.UiState

@Suppress("EqualsOrHashCode")
sealed class SplashState : UiState {
    object Idle : SplashState()
}
