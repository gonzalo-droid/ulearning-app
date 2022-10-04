package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class MessageState : UiState {

    object Idle : MessageState()

    object Loading : MessageState()

    data class Messages constructor(val messages: List<Message>) : MessageState()

}