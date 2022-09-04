package com.ulearning.ulearning_app.data.repository.auth

import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Either.Left
import com.ulearning.ulearning_app.core.functional.Either.Right
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.AuthMapper
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val service: AuthService,
    private val mapper: AuthMapper,
    private val dataStore: DataStoreConfig
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Either<Failure, Boolean> {
        return when (val response =
            service.login(LoginRequest(email = email, password = password))) {
            is Right -> {
                val loginResponse: LoginResponse = response.b

                return if (loginResponse.token.isNotEmpty()) {
                    dataStore.saveToken(token = loginResponse.token)
                    Right(true)
                } else {
                    Left(Failure.DefaultError(R.string.error_user_message))
                }
            }
            is Left -> Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun profile(): Either<Failure, Profile> {
        return when (val response = service.profile(
            token = "${SettingRemote.BEARER} ${dataStore.token()}"
        )) {
            is Right -> {
                Right(mapper.profileToDomain(response.b))
            }
            is Left -> Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun logout(): Either<Failure, Boolean> {

        dataStore.saveUserName("")
        dataStore.saveToken("")

        return if (dataStore.token().isEmpty()) {
            Right(true)
        } else {
            Left(Failure.DefaultError(R.string.error_user_message))
        }
    }
}
