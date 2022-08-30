package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("avatar")
    val avatar: Any? = null,
    @SerializedName("country")
    val country: Any? = null,
    @SerializedName("country_id")
    val countryId: Int? = 0,
    @SerializedName("date_of_birth")
    val dateOfBirth: String? = "",
    @SerializedName("document_number")
    val documentNumber: String? = "",
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
    val permissions: List<String?>? = arrayListOf(),
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("phone_code")
    val phoneCode: String? = "",
    @SerializedName("plan")
    val plan: Any? = null,
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("second_last_name")
    val secondLastName: String? = "",
    @SerializedName("suspended_at")
    val suspendedAt: String? = ""
)