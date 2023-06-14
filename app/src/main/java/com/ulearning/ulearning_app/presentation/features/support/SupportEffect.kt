package com.ulearning.ulearning_app.presentation.features.support

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class SupportEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : SupportEffect()

    object GoToNewConversation : SupportEffect()
}
