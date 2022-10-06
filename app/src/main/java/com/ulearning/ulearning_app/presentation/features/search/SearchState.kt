package com.ulearning.ulearning_app.presentation.features.search

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class SearchState : UiState {

    object Idle : SearchState()

    object Loading : SearchState()

    data class DataConversation constructor(val conversation: Conversation) : SearchState()

    data class UserList constructor(val users: List<User>) : SearchState()

}