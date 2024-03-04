package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Payment(
    var id: Int? = 0,
    var uuid: String? = "",
    var payable: Double? = 0.0,
    var currency: String? = "",
    var status: String? = "",
    var isConfirmed: Boolean? = false,
    var confirmedAt: String? = "",
    var paymentCode: String? = "",
    var items: List<PaymentItem> = arrayListOf(),
) : Serializable {
    fun moneyFormat(): String {
        return "${typeCurrency()} $payable"
    }

    fun typeCurrency(): String {
        return when (currency) {
            "PEN" -> "S/"
            else -> {
                "$"
            }
        }
    }

    fun statusFormat(): String {
        return when (status) {
            "pending" -> "Pago pendiente"
            else -> {
                "Pago realizado"
            }
        }
    }
}
