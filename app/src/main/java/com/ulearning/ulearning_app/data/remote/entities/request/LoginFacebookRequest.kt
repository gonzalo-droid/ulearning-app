package com.ulearning.ulearning_app.data.remote.entities.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginFacebookRequest(
    @SerializedName("email") val email: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("family_name") val familyName: String? = "",
    @SerializedName("given_name") val givenName: String? = "",
) : Serializable