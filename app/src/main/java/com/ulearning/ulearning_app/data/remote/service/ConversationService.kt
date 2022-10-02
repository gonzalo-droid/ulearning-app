package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse


interface ConversationService {

    suspend fun conversations(
        token: String,
        perPage: Int,
        page: Int,
        courseId: Int,
    ): Either<Failure, List<ConversationResponse>>

}