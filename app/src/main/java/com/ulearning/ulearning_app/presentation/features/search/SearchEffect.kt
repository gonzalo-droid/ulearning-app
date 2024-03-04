package com.ulearning.ulearning_app.presentation.features.search

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class SearchEffect : UiEffect {
    data class ShowMessageFailure constructor(val failure: Failure) : SearchEffect()

    data class GoToConversation constructor(val conversation: Conversation) : SearchEffect()
}
