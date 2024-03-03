package com.ulearning.ulearning_app.presentation.features.splash

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class SplashEvent : UiEvent {
    object GotoActivity : SplashEvent()
}
