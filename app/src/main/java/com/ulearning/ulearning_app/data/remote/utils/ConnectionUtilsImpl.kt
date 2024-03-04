package com.ulearning.ulearning_app.data.remote.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionUtilsImpl
    @Inject
    constructor(private val applicationContext: Context) : ConnectionUtils {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun isNetworkAvailable(): Boolean {
            try {
                val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    // for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } catch (e: Exception) {
                Log.e("NetworkUtils", "Exception happened: ${e.message}")
                return false
            }
        }
    }
