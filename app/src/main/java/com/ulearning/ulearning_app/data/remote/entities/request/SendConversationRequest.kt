package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SendConversationRequest(
    @SerializedName("content") val content: String,
    @SerializedName("course_id") val courseId: Int,
    @SerializedName("user_ids") val userIds: ArrayList<Int>,
) : Serializable
