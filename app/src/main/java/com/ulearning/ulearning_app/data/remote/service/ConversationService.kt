package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.SendMessageRequest
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import retrofit2.http.Body


interface ConversationService {

    suspend fun conversations(
        token: String,
        perPage: Int,
        page: Int,
        courseId: Int,
    ): Either<Failure, List<ConversationResponse>>

    suspend fun messages(
        token: String,
        uuid: String,
    ): Either<Failure, List<MessageResponse>>

    suspend fun sendMessage(
        token: String,
        body: SendMessageRequest
    ): Either<Failure, MessageResponse>

}