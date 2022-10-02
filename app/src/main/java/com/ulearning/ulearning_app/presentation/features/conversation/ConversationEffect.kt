package com.ulearning.ulearning_app.presentation.features.conversation

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class ConversationEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : ConversationEffect()
}