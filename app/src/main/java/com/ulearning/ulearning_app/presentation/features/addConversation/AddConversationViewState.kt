package com.ulearning.ulearning_app.presentation.features.addConversation

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User

interface AddConversationViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun conversation(conversation: Conversation)

    fun users(users: List<User>)

    fun getRole(role: String)
}
