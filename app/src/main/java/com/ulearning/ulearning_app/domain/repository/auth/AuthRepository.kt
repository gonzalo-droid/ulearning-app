package com.ulearning.ulearning_app.domain.repository.auth

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String,
    ): Either<Failure, Boolean>
}