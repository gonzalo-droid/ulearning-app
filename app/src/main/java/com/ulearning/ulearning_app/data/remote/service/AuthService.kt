package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.FCMTokenRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginFacebookRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginGoogleRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.FCMTokenResponse
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    suspend fun login(body: LoginRequest): Either<Failure, LoginResponse>

    suspend fun profile(token: String): Either<Failure, ProfileResponse>

    suspend fun fcmToken(token: String, body: FCMTokenRequest): Either<Failure, FCMTokenResponse>

    suspend fun selfAuthToken(token: String): Either<Failure, TokenResponse>

    suspend fun loginGoogle(body: LoginGoogleRequest): Either<Failure, LoginResponse>

    suspend fun loginFacebook(body: LoginFacebookRequest): Either<Failure, LoginResponse>

}