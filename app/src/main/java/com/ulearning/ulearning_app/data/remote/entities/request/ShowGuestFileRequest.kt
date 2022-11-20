package com.ulearning.ulearning_app.data.remote.entities.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShowGuestFileRequest(
    @SerializedName("name") val name: String,
) : Serializable