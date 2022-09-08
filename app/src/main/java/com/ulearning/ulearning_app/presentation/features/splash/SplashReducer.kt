package com.ulearning.ulearning_app.presentation.features.splash

object SplashReducer {

    private lateinit var viewState: SplashViewState

    fun instance(splashViewState: SplashViewState) {

        viewState = splashViewState
    }

    fun selectState(state: SplashState) {
        when (state) {
            is SplashState.Idle -> {}
        }
    }

    fun selectEffect(effect: SplashEffect) {
        when (effect) {
            is SplashEffect.GoToHome -> viewState.goHome()
            is SplashEffect.GoToLogin -> viewState.goLogin()
            else -> {}
        }
    }
}