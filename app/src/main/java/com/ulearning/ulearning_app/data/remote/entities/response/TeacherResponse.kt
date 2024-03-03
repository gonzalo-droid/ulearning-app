package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class TeacherResponse(
    @SerializedName("avatar")
    val avatar: Any? = null,
    @SerializedName("first_name")
    val firstName: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("last_name")
    val lastName: String? = "",
    @SerializedName("subtype")
    val subtype: String? = "",
    @SerializedName("type")
    val type: String? = ""
)
