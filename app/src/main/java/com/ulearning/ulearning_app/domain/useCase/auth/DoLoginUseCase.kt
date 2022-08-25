package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject


class DoLoginUseCase
@Inject constructor(private val authRepository: AuthRepository) : BaseUseCase<Boolean, DoLoginUseCase.Params>() {

    override suspend fun run(params: Params) = authRepository.login(params.username, params.password)

    data class Params(val username: String, val password: String)
}
