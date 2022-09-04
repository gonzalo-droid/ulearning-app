package com.ulearning.ulearning_app.presentation.features.profile


object ProfileReducer {

    private lateinit var viewState: ProfileViewState

    fun instance(viewState: ProfileViewState) {
        this.viewState = viewState
    }

    fun selectState(state: ProfileState) {
        when (state) {
            is ProfileState.Idle -> {}

            is ProfileState.Loading -> viewState.loading()

            is ProfileState.DatProfile -> {
                viewState.getProfile(data = state.data)
            }
        }
    }

    fun selectEffect(effect: ProfileEffect) {
        when (effect) {
            is ProfileEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is ProfileEffect.Logout -> viewState.logout()
            else -> {}
        }
    }
}