package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName


data class FirstMessageResponse(
    @SerializedName("classification")
    val classification: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("published_at")
    val publishedAt: String? = "",
    @SerializedName("send_by")
    val sendBy: UserResponse? = UserResponse(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("subject")
    val subject: Any? = Any(),
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("user_ids")
    val userIds: List<String?>? = arrayListOf(),
    @SerializedName("uuid")
    val uuid: String? = ""
)