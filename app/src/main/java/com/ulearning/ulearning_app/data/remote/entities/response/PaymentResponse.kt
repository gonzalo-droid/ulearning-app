package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("uuid")
    var uuid: String,
    @SerializedName("payable")
    var payable: Double? = 0.0,
    @SerializedName("currency")
    var currency: String? = "",
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("is_confirmed")
    var isConfirmed: Boolean? = false,
    @SerializedName("confirmed_at")
    var confirmedAt: String? = "",
    @SerializedName("payment_ode")
    var paymentCode: String,
    @SerializedName("items")
    var items: ArrayList<PaymentItemResponse> = arrayListOf(),
)
