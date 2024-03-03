package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class FileInfoResponse(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("hours")
    val hours: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("period")
    val period: Any? = Any(),
    @SerializedName("rating")
    val rating: Int? = 0,
    @SerializedName("send_mail")
    val sendMail: Boolean? = false,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("topics")
    val topics: String? = ""
)
