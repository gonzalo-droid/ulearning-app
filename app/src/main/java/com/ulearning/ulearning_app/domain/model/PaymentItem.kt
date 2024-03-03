package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class PaymentItem(
    var paymentId: Int? = 0,
    var title: String? = "",
    var amount: Double? = 0.0,
    var payable: Double? = 0.0,
) : Serializable