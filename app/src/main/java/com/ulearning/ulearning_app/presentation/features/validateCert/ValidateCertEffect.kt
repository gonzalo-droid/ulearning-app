package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class ValidateCertEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : ValidateCertEffect()
}
