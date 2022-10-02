package com.ulearning.ulearning_app.presentation.features.conversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class ConversationState : UiState {

    object Idle : ConversationState()

    object Loading : ConversationState()

    data class Conversations constructor(val conversations: List<Conversation>) : ConversationState()

}