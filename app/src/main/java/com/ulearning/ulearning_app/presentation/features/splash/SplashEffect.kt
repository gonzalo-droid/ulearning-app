package com.ulearning.ulearning_app.presentation.features.splash

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect
sealed class SplashEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : SplashEffect()

    object GoToLogin : SplashEffect()

    object GoToHome : SplashEffect()
}
