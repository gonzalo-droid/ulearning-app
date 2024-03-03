package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class PaymentEffect : UiEffect {
    data class ShowMessageFailure constructor(val failure: Failure) : PaymentEffect()
}
