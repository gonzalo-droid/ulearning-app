package com.ulearning.ulearning_app.data.remote.entities.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginFacebookRequest(
    @SerializedName("email") val email: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("first_name") val firstName: String? = "",
    @SerializedName("last_name") val lastName: String? = "",
    @SerializedName("picture") val picture: String? = "",
) : Serializable
