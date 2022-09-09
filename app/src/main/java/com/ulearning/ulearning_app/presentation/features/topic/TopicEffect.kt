package com.ulearning.ulearning_app.presentation.features.topic

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class TopicEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : TopicEffect()
}