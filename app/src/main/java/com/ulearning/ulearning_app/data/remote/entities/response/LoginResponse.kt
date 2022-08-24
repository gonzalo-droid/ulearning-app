package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("username")  val userName: String,
) : Serializable

