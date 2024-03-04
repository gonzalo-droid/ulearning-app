package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class ConversationResponse(
    @SerializedName("can_by_reply")
    val canByReply: Boolean? = false,
    @SerializedName("course_id")
    val courseId: Int? = 0,
    @SerializedName("firstMessage")
    val firstMessage: FirstMessageResponse? = FirstMessageResponse(),
    @SerializedName("createdBy")
    val createdBy: UserResponse? = UserResponse(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("is_broadcast")
    val isBroadcast: Boolean? = false,
    @SerializedName("reply_to_author")
    val replyToAuthor: Boolean? = false,
    @SerializedName("to_support")
    val toSupport: Boolean? = false,
    @SerializedName("uuid")
    val uuid: String,
)
