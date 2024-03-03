package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DownloadGuestFileRequest(
    @SerializedName("name") val name: String,
    @SerializedName("hash") val hash: String,
) : Serializable
