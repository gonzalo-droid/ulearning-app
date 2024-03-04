package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class MessageEffect : UiEffect {
    data class ShowMessageFailure constructor(val failure: Failure) : MessageEffect()
}
