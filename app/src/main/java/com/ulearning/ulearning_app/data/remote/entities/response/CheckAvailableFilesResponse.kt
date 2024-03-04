package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class CheckAvailableFilesResponse(
    @SerializedName("certificate")
    val certificate: Boolean? = false,
    @SerializedName("record")
    val record: Boolean? = false,
)
