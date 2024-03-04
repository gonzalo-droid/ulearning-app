package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationSupportRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendMessageRequest
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.UserResponse

interface ConversationService {
    suspend fun conversations(
        token: String,
        perPage: Int,
        page: Int,
        courseId: Int,
    ): Either<Failure, List<ConversationResponse>>

    suspend fun conversationsSupport(
        token: String,
        perPage: Int,
        page: Int,
        toSupport: Boolean,
    ): Either<Failure, List<ConversationResponse>>

    suspend fun participantsMessage(
        token: String,
        ids: String,
    ): Either<Failure, List<UserResponse>>

    suspend fun messages(
        token: String,
        uuid: String,
    ): Either<Failure, List<MessageResponse>>

    suspend fun sendMessage(
        token: String,
        body: SendMessageRequest,
    ): Either<Failure, MessageResponse>

    suspend fun sendConversation(
        token: String,
        body: SendConversationRequest,
    ): Either<Failure, ConversationResponse>

    suspend fun sendConversationSupport(
        token: String,
        body: SendConversationSupportRequest,
    ): Either<Failure, ConversationResponse>

    suspend fun userByCourse(
        token: String,
        name: String,
        courseId: Int,
        withoutPagination: Boolean,
    ): Either<Failure, List<UserResponse>>

    suspend fun userByIds(
        token: String,
        ids: String,
        courseId: Int,
    ): Either<Failure, List<UserResponse>>
}
