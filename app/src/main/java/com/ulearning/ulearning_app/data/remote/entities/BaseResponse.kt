package com.ulearning.ulearning_app.data.remote.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse<T>(
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T,
    @SerializedName("isSuccess") val success: Boolean,
    @SerializedName("token") val token: String?
) : Serializable