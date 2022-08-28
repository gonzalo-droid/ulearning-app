package com.ulearning.ulearning_app.data.remote.entities.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubscriptionRequest(
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("page") val pafe: Int,
    @SerializedName("is_finished") val isFinished: Boolean,
) : Serializable
