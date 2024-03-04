package com.ulearning.ulearning_app.presentation.features.addConversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class AddConversationState : UiState {
    object Idle : AddConversationState()

    object Loading : AddConversationState()

    data class DataConversation constructor(val conversation: Conversation) : AddConversationState()

    data class UserList constructor(val users: List<User>) : AddConversationState()

    data class GetRole constructor(val role: String) : AddConversationState()
}
