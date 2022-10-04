package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Message

interface MessageViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun messages(messages: List<Message>)

}