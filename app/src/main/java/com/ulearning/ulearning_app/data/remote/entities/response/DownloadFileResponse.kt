package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class DownloadFileResponse(
    @SerializedName("file")
    val file: String? = "",
    @SerializedName("filename")
    val filename: String? = "",
    @SerializedName("file_url")
    val fileUrl: String? = "",
)
