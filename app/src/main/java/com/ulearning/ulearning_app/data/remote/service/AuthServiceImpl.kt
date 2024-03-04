package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.AuthApi
import com.ulearning.ulearning_app.data.remote.entities.request.FCMTokenRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginFacebookRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginGoogleRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.FCMTokenResponse
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TokenResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl
    @Inject
    constructor(
        private val authApi: AuthApi,
        private val networkHandler: NetworkHandler,
    ) : AuthService {
        override suspend fun login(body: LoginRequest): Either<Failure, LoginResponse> {
            return networkHandler.callService { authApi.login(body) }
        }

        override suspend fun profile(token: String): Either<Failure, ProfileResponse> {
            return networkHandler.callServiceBase { authApi.profile(token) }
        }

        override suspend fun fcmToken(
            token: String,
            body: FCMTokenRequest,
        ): Either<Failure, FCMTokenResponse> {
            return networkHandler.callServiceBase { authApi.fcmToken(token, body) }
        }

        override suspend fun selfAuthToken(token: String): Either<Failure, TokenResponse> {
            return networkHandler.callServiceBase { authApi.selfAuthToken(token) }
        }

        override suspend fun loginGoogle(body: LoginGoogleRequest): Either<Failure, LoginResponse> {
            return networkHandler.callService { authApi.loginGoogle(body) }
        }

        override suspend fun loginFacebook(body: LoginFacebookRequest): Either<Failure, LoginResponse> {
            return networkHandler.callService { authApi.loginFacebook(body) }
        }
    }
