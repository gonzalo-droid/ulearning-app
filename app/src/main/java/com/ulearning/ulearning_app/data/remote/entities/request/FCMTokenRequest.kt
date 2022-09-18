package com.ulearning.ulearning_app.data.remote.entities.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FCMTokenRequest(
    @SerializedName("device_id") val deviceId: String,
    @SerializedName("fcm_token") val fcmToken: String,
) : Serializable