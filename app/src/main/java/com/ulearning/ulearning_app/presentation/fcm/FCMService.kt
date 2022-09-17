package com.ulearning.ulearning_app.presentation.fcm

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ulearning.ulearning_app.core.utils.Config

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.i("TOKEN FCM", token)

        Log.i("TOKEN ID", Config.DEVICE_ID)


    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

    }
}