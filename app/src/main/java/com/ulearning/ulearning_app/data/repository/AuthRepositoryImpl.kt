package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.BuildConfig
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Either.Left
import com.ulearning.ulearning_app.core.functional.Either.Right
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.AuthMapper
import com.ulearning.ulearning_app.data.remote.entities.request.FCMTokenRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginFacebookRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginGoogleRequest
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.presentation.model.entity.LoginFacebook
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle
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
            is Left -> Left(Failure.DefaultError(R.string.error_credentials))
        }
    }

    override suspend fun loginGoogle(data: LoginGoogle): Either<Failure, Boolean> {
        return when (val response =
            service.loginGoogle(
                LoginGoogleRequest(
                    email = data.email,
                    name = data.name,
                    familyName = data.familyName,
                    givenName = data.givenName
                )
            )
        ) {
            is Right -> {
                val loginResponse: LoginResponse = response.b

                return if (loginResponse.token.isNotEmpty()) {
                    dataStore.saveToken(token = loginResponse.token)
                    Right(true)
                } else {
                    Left(Failure.DefaultError(R.string.error_user_message))
                }
            }
            is Left -> Left(Failure.DefaultError(R.string.error_credentials))
        }
    }

    override suspend fun loginFacebook(data: LoginFacebook): Either<Failure, Boolean> {
        return when (val response =
            service.loginFacebook(
                LoginFacebookRequest(
                    email = data.email,
                    name = data.name,
                    firstName = data.firstName,
                    lastName = data.lastName,
                    picture = data.picture,
                )
            )
        ) {
            is Right -> {
                val loginResponse: LoginResponse = response.b

                return if (loginResponse.token.isNotEmpty()) {
                    dataStore.saveToken(token = loginResponse.token)
                    Right(true)
                } else {
                    Left(Failure.DefaultError(R.string.error_user_message))
                }
            }
            is Left -> Left(Failure.DefaultError(R.string.error_credentials))
        }
    }


    override suspend fun profile(): Either<Failure, Profile> {
        return when (val response = service.profile(
            token = "${SettingRemote.BEARER} ${dataStore.token()}"
        )) {
            is Right -> {
                val profile = mapper.profileToDomain(response.b)
                profile.role?.let { dataStore.saveRole(role = it) }
                profile.name?.let { dataStore.saveUserName(userName = it) }
                profile.id?.let { dataStore.saveId(id = it) }
                Right(profile)
            }
            is Left -> Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun getRole(): Either<Failure, String> {
        return if (dataStore.role().isNotEmpty()) {
            Right(dataStore.role())
        } else {
            Right(Config.ROLE_STUDENT)
        }
    }

    override suspend fun getUserId(): Either<Failure, Int> {
        return if (dataStore.id() != 0) {
            Right(dataStore.id())
        } else {
            Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun logout(): Either<Failure, Boolean> {

        dataStore.saveUserName("")
        dataStore.saveToken("")
        dataStore.saveRole("")
        dataStore.saveId(0)

        return if (dataStore.token().isEmpty()) {
            Right(true)
        } else {
            Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun session(): Either<Failure, Boolean> {
        return if (dataStore.token().isNotEmpty()) {
            Right(true)
        } else {
            Right(false)
        }
    }

    override suspend fun fcmToken(fcmToken: String): Either<Failure, Boolean> {
        return when (val response = service.fcmToken(
            token = "${SettingRemote.BEARER} ${dataStore.token()}",
            body = FCMTokenRequest(deviceId = Config.DEVICE_ID, fcmToken = fcmToken)
        )) {
            is Right -> {
                Right(true)
            }
            is Left -> Left(Failure.DefaultError(R.string.error_user_message))
        }
    }

    override suspend fun selfAuthToken(): Either<Failure, String> {
        return when (val response = service.selfAuthToken(
            token = "${SettingRemote.BEARER} ${dataStore.token()}"
        )) {
            is Right -> {
                val url = "${BuildConfig.STUDENT_URL}/sessions/signin-token/${response.b.token}"
                Right(url)
            }
            is Left -> Left(Failure.DefaultError(R.string.error_user_message))
        }
    }
}