package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("expired_at") val expiredDate: String
) : Serializable
