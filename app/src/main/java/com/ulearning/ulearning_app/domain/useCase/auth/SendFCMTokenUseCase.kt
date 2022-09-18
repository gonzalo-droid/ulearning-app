package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class SendFCMTokenUseCase
@Inject constructor(private val authRepository: AuthRepository) :
    BaseUseCase<Boolean, SendFCMTokenUseCase.Params>() {

    override suspend fun run(params: Params) = authRepository.fcmToken(params.fcmToken)

    data class Params(val fcmToken: String)
}
