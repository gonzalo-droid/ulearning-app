package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("address")
    val address: Any? = Any(),
    @SerializedName("avatar")
    val avatar: Any? = Any(),
    @SerializedName("country")
    val country: Any? = Any(),
    @SerializedName("country_id")
    val countryId: Any? = Any(),
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
    @SerializedName("last_name")
    val lastName: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("permissions")
    val permissions: List<String?>? = listOf(),
    @SerializedName("phone")
    val phone: Any? = Any(),
    @SerializedName("phone_code")
    val phoneCode: Any? = Any(),
    @SerializedName("plan")
    val plan: Any? = Any(),
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("second_last_name")
    val secondLastName: Any? = Any(),
    @SerializedName("suspended_at")
    val suspendedAt: Any? = Any()
)