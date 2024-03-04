package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.presentation.model.entity.LoginFacebook
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String,
    ): Either<Failure, Boolean>

    suspend fun loginGoogle(data: LoginGoogle): Either<Failure, Boolean>

    suspend fun loginFacebook(data: LoginFacebook): Either<Failure, Boolean>

    suspend fun profile(): Either<Failure, Profile>

    suspend fun logout(): Either<Failure, Boolean>

    suspend fun session(): Either<Failure, Boolean>

    suspend fun getRole(): Either<Failure, String>

    suspend fun getUserId(): Either<Failure, Int>

    suspend fun fcmToken(fcmToken: String): Either<Failure, Boolean>

    suspend fun selfAuthToken(): Either<Failure, String>
}
