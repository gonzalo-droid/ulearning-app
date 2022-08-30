package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse


interface AuthService {
    
    suspend fun login(body: LoginRequest) : Either<Failure, LoginResponse>

    suspend fun profile(token: String) : Either<Failure, ProfileResponse>

}