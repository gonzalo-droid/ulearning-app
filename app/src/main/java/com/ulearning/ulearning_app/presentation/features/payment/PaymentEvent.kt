package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class PaymentEvent : UiEvent {

    object PaymentClicked : PaymentEvent()

}
