package com.ulearning.ulearning_app.presentation.features.conversation

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation

interface ConversationViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun conversations(conversations: List<Conversation>)

}