package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.presentation.features.addConversation.AddConversationReducer
import com.ulearning.ulearning_app.presentation.features.addConversation.AddConversationState


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
        }
    }

    fun selectEffect(effect: MessageEffect) {
        when (effect) {
            is MessageEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)

        }
    }
}