package com.ulearning.ulearning_app.domain.repository.auth

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String,
    ): Either<Failure, Boolean>

    suspend fun profile(): Either<Failure, Profile>
}