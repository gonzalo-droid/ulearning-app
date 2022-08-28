package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SubscriptionRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseApi {

    @GET("subscriptions")
    suspend fun subscription(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body request: SubscriptionRequest
    ): Response<BaseResponse<List<SubscriptionResponse>>>
}