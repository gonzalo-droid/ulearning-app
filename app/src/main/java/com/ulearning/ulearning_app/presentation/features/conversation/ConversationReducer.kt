package com.ulearning.ulearning_app.presentation.features.conversation

object ConversationReducer {

    private lateinit var viewState: ConversationViewState

    fun instance(viewState: ConversationViewState) {
        this.viewState = viewState
    }

    fun selectState(state: ConversationState) {
        when (state) {
            is ConversationState.Idle -> {}

            is ConversationState.Loading -> viewState.loading()
            is ConversationState.Conversations -> viewState.conversations(state.conversations)
        }
    }

    fun selectEffect(effect: ConversationEffect) {
        when (effect) {
            is ConversationEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is ConversationEffect.GoToNewConversation -> viewState.newConversation()
        }
    }
}
