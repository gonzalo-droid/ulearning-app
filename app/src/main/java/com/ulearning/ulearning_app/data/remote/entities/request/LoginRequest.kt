package com.ulearning.ulearning_app.data.remote.entities.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginRequest(
    @SerializedName("username")  val userName: String,
    @SerializedName("password")  val password: String,
) : Serializable