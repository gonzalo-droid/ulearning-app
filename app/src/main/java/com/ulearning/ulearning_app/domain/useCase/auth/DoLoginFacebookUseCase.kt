package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.presentation.model.entity.LoginFacebook
import javax.inject.Inject

class DoLoginFacebookUseCase
    @Inject
    constructor(private val authRepository: AuthRepository) : BaseUseCase<Boolean, DoLoginFacebookUseCase.Params>() {
        override suspend fun run(params: Params) = authRepository.loginFacebook(params.data)

        data class Params(val data: LoginFacebook)
    }
