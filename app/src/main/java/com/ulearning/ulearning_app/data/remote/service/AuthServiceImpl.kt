package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.AuthApi
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl
    @Inject constructor(
        private val authApi: AuthApi,
        private val networkHandler: NetworkHandler
    ) : AuthService {

    override suspend fun login(body: LoginRequest): Either<Failure, LoginResponse> {
        return networkHandler.callService { authApi.login(body) }
    }

}