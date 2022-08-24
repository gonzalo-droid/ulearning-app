package com.ulearning.ulearning_app.data.remote.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupportInterceptor @Inject constructor(): Interceptor {
    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", SettingRemote.CONTENT_TYPE_JSON)
            .addHeader("Accept", SettingRemote.ACCEPT_JSON)
            .addHeader(SettingRemote.HEADER_AUTH_KEY, SettingRemote.HEADER_AUTH_VALUE)
            .build()
        return chain.proceed(request)
    }
}