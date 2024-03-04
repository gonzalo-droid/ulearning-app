package com.ulearning.ulearning_app.presentation.features.support

object SupportReducer {
    private lateinit var viewState: SupportViewState

    fun instance(viewState: SupportViewState) {
        SupportReducer.viewState = viewState
    }

    fun selectState(state: SupportState) {
        when (state) {
            is SupportState.Idle -> {}

            is SupportState.Loading -> viewState.loading()
            is SupportState.Conversations -> viewState.conversations(state.conversations)
        }
    }

    fun selectEffect(effect: SupportEffect) {
        when (effect) {
            is SupportEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is SupportEffect.GoToNewConversation -> viewState.newConversation()
        }
    }
}
