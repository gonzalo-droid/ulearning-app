package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class DoLoginUseCase
@Inject constructor(private val authRepository: AuthRepository) : BaseUseCase<Boolean, DoLoginUseCase.Params>() {

    override suspend fun run(params: Params) = authRepository.login(params.email, params.password)

    data class Params(val email: String, val password: String)
}
