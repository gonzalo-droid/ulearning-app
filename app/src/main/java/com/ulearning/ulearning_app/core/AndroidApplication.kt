package com.ulearning.ulearning_app.core

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.ulearning.ulearning_app.R
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        val options = FirebaseOptions.Builder()
            .setApplicationId(getString(R.string.google_app_id))//FIREBASE_APPLICATION_ID
            .setProjectId(getString(R.string.gcm_defaultSenderId))//FIREBASE_PROJECT_ID
            .setGcmSenderId(getString(R.string.project_id))//FIREBASE_GCM_SENDER_ID
            .setApiKey(getString(R.string.google_api_key))//FIREBASE_APIKEY_ID
            .build()

        FirebaseApp.initializeApp(applicationContext, options,"APP NAME")
    }
}