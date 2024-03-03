package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class PaymentItemResponse(
    @SerializedName("payment_id")
    var paymentId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("amount")
    var amount: Double? = 0.0,
    @SerializedName("payable")
    var payable: Double? = 0.0,
)