package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class ProfileEffect : UiEffect {

    object Logout : ProfileEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : ProfileEffect()
}