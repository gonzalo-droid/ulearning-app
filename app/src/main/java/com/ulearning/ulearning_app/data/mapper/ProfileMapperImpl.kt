package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.PaymentItemResponse
import com.ulearning.ulearning_app.data.remote.entities.response.PaymentResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.PaymentItem
import com.ulearning.ulearning_app.domain.model.Profile
import javax.inject.Singleton

@Singleton
class ProfileMapperImpl : ProfileMapper {
    override suspend fun listPaymentToDomain(data: List<PaymentResponse>): List<Payment> {
        return data.map {
            Payment(
                id = it.id,
                uuid = it.uuid,
                payable = it.payable,
                currency = it.currency,
                status = it.status,
                isConfirmed = it.isConfirmed,
                confirmedAt = it.confirmedAt,
                paymentCode = it.paymentCode,
                items = if (it.items.isNotEmpty()) paymentItemToDomain(it.items) else arrayListOf(),
            )
        }
    }

    override suspend fun paymentItemToDomain(data: List<PaymentItemResponse>): List<PaymentItem> {
        return data.map {
            PaymentItem(
                paymentId = it.paymentId,
                title = it.title,
                amount = it.amount,
                payable = it.payable,
            )
        }
    }

    override suspend fun profileToDomain(data: ProfileResponse): Profile {
        return data.let {
            Profile(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                secondLastName = it.secondLastName,
                address = it.address,
                documentNumber = it.documentNumber,
                documentType = it.documentType,
                email = it.email,
                phone = it.phone,
                phoneCode = it.phoneCode,
                dateOfBirth = it.dateOfBirth,
            )
        }
    }
}