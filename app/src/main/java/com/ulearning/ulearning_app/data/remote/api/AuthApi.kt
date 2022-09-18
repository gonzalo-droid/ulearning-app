package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.FCMTokenRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.FCMTokenResponse
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*


interface AuthApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("profile")
    suspend fun profile(
        @Header(SettingRemote.AUTHORIZATION) token: String,
    ): Response<BaseResponse<ProfileResponse>>

    @GET("fmc-token")
    suspend fun fcmToken(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body request: FCMTokenRequest
    ): Response<BaseResponse<FCMTokenResponse>>
    
}