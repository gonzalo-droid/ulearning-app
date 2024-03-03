package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FCMTokenResponse(
    @SerializedName("device_id") val deviceId: String,
    @SerializedName("fcm_token") val fcmToken: String,
) : Serializable
