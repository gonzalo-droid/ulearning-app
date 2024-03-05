package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileRequest(

    @SerializedName("date_of_birth")
    var dateOfBirth: String? = "",
    @SerializedName("document_number")
    var documentNumber: String? = "",
    @SerializedName("document_type")
    var documentType: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("first_name")
    var firstName: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("last_name")
    var lastName: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("phone_code")
    var phoneCode: String? = "",
    @SerializedName("role")
    var role: String? = "",
    @SerializedName("second_last_name")
    var secondLastName: String? = "",
) : Serializable


