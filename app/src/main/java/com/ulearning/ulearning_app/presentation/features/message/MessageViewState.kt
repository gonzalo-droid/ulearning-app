package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User

interface MessageViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun supportUser()

    fun messages(messages: List<Message>)

    fun users(users: List<User>)

    fun userId(userId: Int)
}
