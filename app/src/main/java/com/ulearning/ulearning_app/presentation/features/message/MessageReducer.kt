package com.ulearning.ulearning_app.presentation.features.message

object MessageReducer {
    private lateinit var viewState: MessageViewState

    fun instance(viewState: MessageViewState) {
        this.viewState = viewState
    }

    fun selectState(state: MessageState) {
        when (state) {
            is MessageState.Idle -> {}
            is MessageState.GetParticipants -> viewState.users(state.users)
            is MessageState.Loading -> viewState.loading()
            is MessageState.Messages -> viewState.messages(state.messages)
            is MessageState.GetUserId -> viewState.userId(state.userId)
            MessageState.SupportUser -> viewState.supportUser()
        }
    }

    fun selectEffect(effect: MessageEffect) {
        when (effect) {
            is MessageEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
        }
    }
}
