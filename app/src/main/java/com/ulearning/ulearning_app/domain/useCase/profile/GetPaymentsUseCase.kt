package com.ulearning.ulearning_app.domain.useCase.profile

import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.repository.ProfileRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetPaymentsUseCase
    @Inject
    constructor(private val repository: ProfileRepository) :
    BaseUseCase<List<Payment>, GetPaymentsUseCase.Params>() {
        override suspend fun run(params: Params) = repository.getPayment(params.page)

        data class Params(val page: Int)
    }
