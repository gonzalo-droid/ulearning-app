package com.ulearning.ulearning_app.presentation.features.support

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation

interface SupportViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun conversations(conversations: List<Conversation>)

    fun newConversation()
}
