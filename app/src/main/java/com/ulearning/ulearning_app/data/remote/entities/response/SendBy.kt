package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class SendBy(
    @SerializedName("address")
    val address: Any? = Any(),
    @SerializedName("date_of_birth")
    val dateOfBirth: Any? = Any(),
    @SerializedName("document_number")
    val documentNumber: Any? = Any(),
    @SerializedName("document_type")
    val documentType: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("first_name")
    val firstName: String? = "",
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("is_support")
    val isSupport: Boolean? = false,
    @SerializedName("last_name")
    val lastName: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("phone_code")
    val phoneCode: String? = "",
    @SerializedName("plan")
    val plan: PlanResponse? = PlanResponse(),
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("second_last_name")
    val secondLastName: String? = "",
)
