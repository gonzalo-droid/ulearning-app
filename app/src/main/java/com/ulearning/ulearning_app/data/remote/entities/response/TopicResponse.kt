package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("children")
    val children: List<TopicResponse>? = arrayListOf(),
    @SerializedName("course_id")
    val courseId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("order")
    val order: Int? = 0,
    @SerializedName("parent_id")
    val parentId: Int? = null,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
)
