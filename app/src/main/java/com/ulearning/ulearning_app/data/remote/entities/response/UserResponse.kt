package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("address")
    var address: Any?= null,
    @SerializedName("avatar")
    var avatar: Any?= null,
    @SerializedName("country")
    var country: CountryResponse?= null,
    @SerializedName("country_id")
    var countryId: Int?= 0,
    @SerializedName("date_of_birth")
    var dateOfBirth: String?= "",
    @SerializedName("document_number")
    var documentNumber: String?= "",
    @SerializedName("document_type")
    var documentType: String?= "",
    @SerializedName("email")
    var email: String?= "",
    @SerializedName("first_name")
    var firstName: String?= "",
    @SerializedName("gender")
    var gender: String?= "",
    @SerializedName("id")
    var id: Int?= 0,
    @SerializedName("last_name")
    var lastName: String?= "",
    @SerializedName("name")
    var name: String?= "",
    @SerializedName("permissions")
    var permissions: List<String>?= arrayListOf(),
    @SerializedName("phone")
    var phone: String?= "",
    @SerializedName("phone_code")
    var phoneCode: Any?= null,
    @SerializedName("plan")
    var planResponse: PlanResponse?= null,
    @SerializedName("role")
    var role: String?= "",
    @SerializedName("second_last_name")
    var secondLastName: String?= "",
    @SerializedName("suspended_at")
    var suspendedAt: Any?= null
)