package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Payment

interface PaymentViewState {
    fun messageFailure(failure: Failure)

    fun loading()

    fun getPayments(payments: List<Payment>)
}
