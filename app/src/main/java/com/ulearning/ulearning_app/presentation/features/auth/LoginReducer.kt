package com.ulearning.ulearning_app.presentation.features.auth


object LoginReducer {

    private lateinit var viewState: LoginViewState

    fun instance(viewState: LoginViewState) {
        this.viewState = viewState
    }

    fun selectState(state: LoginState) {
        when (state) {
            is LoginState.Idle -> {}
            is LoginState.Loading -> viewState.loading()
            is LoginState.LoginSuccess -> {
                viewState.homeActivity()
            }
        }
    }

    fun selectEffect(effect: LoginEffect) {
        when (effect) {
            is LoginEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is LoginEffect.LoginGoogleEffect -> viewState.loginInGoogle()
            is LoginEffect.LoginFacebookEffect -> viewState.loginInFacebook()
            else -> {}
        }
    }
}