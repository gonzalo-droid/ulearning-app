package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class LoginEffect : UiEffect {

    object ShowSuccess : LoginEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : LoginEffect()
}