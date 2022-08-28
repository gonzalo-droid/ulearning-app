package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class HomeEffect : UiEffect {

    object ShowSuccess : HomeEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : HomeEffect()
}