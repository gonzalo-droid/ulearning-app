package com.ulearning.ulearning_app.data.repository.auth

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Either.Left
import com.ulearning.ulearning_app.core.functional.Either.Right
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.AuthMapper
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val service: AuthService,
    private val mapper: AuthMapper,
    private val dataStore: DataStoreConfig
) : AuthRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Either<Failure, Boolean> {
        return when (val response =
            service.login(LoginRequest(userName = username, password = password))) {
            is Right -> {
                val loginResponse : LoginResponse = response.b
                return if (loginResponse.token.isEmpty()) {
                    dataStore.saveToken(token = loginResponse.token)
                    Right(true)
                } else {
                    Left(Failure.DefaultError("Credenciales incorrectas"))
                }
            }
            is Left -> Left(response.a)
        }
    }
}