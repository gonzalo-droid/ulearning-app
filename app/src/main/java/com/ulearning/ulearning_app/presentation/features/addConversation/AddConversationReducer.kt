package com.ulearning.ulearning_app.presentation.features.addConversation


object AddConversationReducer {

    private lateinit var viewState: AddConversationViewState

    fun instance(viewState: AddConversationViewState) {
        this.viewState = viewState
    }

    fun selectState(state: AddConversationState) {
        when (state) {
            is AddConversationState.Idle -> {}

            is AddConversationState.Loading -> viewState.loading()
            is AddConversationState.DataConversation -> viewState.conversation(state.conversation)
            is AddConversationState.UserList -> viewState.users(state.users)
        }
    }

    fun selectEffect(effect: AddConversationEffect) {
        when (effect) {
            is AddConversationEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is AddConversationEffect.GoToConversation -> TODO()
        }
    }
}