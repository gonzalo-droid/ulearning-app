package com.ulearning.ulearning_app.presentation.features.addConversation

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.UiEffect
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseEffect

sealed class AddConversationEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : AddConversationEffect()

    data class GoToConversation constructor(val conversation: Conversation) : AddConversationEffect()
}