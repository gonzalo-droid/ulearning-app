package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class MessageState : UiState {

    object Idle : MessageState()

    object Loading : MessageState()

    data class Conversations constructor(val conversations: List<Conversation>) : MessageState()

}