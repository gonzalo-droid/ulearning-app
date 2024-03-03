package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SendConversationSupportRequest(
    @SerializedName("content") val content: String,
    @SerializedName("to_support") val toSupport: Boolean,
    @SerializedName("user_ids") val userIds: ArrayList<Int>,
) : Serializable
