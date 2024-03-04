package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetUserIdUseCase
    @Inject
    constructor(private val authRepository: AuthRepository) :
    BaseUseCase<Int, BaseUseCase.None>() {
        override suspend fun run(params: None) = authRepository.getUserId()
    }
