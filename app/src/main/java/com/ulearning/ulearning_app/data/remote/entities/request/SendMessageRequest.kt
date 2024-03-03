package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SendMessageRequest(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("content") val content: String,
    @SerializedName("user_ids") val userIds: ArrayList<String>,
    @SerializedName("to_support") val toSupport: Boolean
) : Serializable
