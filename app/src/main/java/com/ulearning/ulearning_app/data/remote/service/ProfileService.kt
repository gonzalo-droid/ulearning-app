package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.PaymentResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse

interface ProfileService {
    suspend fun payments(
        token: String,
        perPage: Int,
        page: Int,
    ): Either<Failure, List<PaymentResponse>>
}
