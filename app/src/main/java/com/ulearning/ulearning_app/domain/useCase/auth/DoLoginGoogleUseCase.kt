package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle
import javax.inject.Inject

class DoLoginGoogleUseCase
@Inject constructor(private val authRepository: AuthRepository) :
    BaseUseCase<Boolean, DoLoginGoogleUseCase.Params>() {

    override suspend fun run(params: Params) = authRepository.loginGoogle(params.data)

    data class Params(val data: LoginGoogle)
}
