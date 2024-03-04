package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class PaymentState : UiState {
    object Idle : PaymentState()

    object Loading : PaymentState()

    data class PaymentList(val items: List<Payment>) : PaymentState()
}
