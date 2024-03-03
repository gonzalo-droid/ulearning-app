package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.PaymentItemResponse
import com.ulearning.ulearning_app.data.remote.entities.response.PaymentResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.PaymentItem
import com.ulearning.ulearning_app.domain.model.Topic

interface ProfileMapper {

    suspend fun listPaymentToDomain(data: List<PaymentResponse>): List<Payment>

    suspend fun paymentItemToDomain(data: List<PaymentItemResponse>): List<PaymentItem>

}
