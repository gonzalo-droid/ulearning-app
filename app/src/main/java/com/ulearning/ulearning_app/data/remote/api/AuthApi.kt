package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.FCMTokenRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginFacebookRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginGoogleRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest,
    ): Response<LoginResponse>

    @GET("profile")
    suspend fun profile(
        @Header(SettingRemote.AUTHORIZATION) token: String,
    ): Response<BaseResponse<ProfileResponse>>

    @POST("fcm-token")
    suspend fun fcmToken(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body request: FCMTokenRequest,
    ): Response<BaseResponse<FCMTokenResponse>>

    @POST("self-auth-token")
    suspend fun selfAuthToken(
        @Header(SettingRemote.AUTHORIZATION) token: String,
    ): Response<BaseResponse<TokenResponse>>

    @POST("login-google")
    suspend fun loginGoogle(
        @Body request: LoginGoogleRequest,
    ): Response<LoginResponse>

    @POST("login-facebook")
    suspend fun loginFacebook(
        @Body request: LoginFacebookRequest,
    ): Response<LoginResponse>
}
