package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.ConversationApi
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendMessageRequest
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.UserResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationServiceImpl
@Inject constructor(
    private val api: ConversationApi,
    private val networkHandler: NetworkHandler
) : ConversationService {

    override suspend fun conversations(
        token: String,
        perPage: Int,
        page: Int,
        courseId: Int
    ): Either<Failure, List<ConversationResponse>> {
        return networkHandler.callServiceBaseList {
            api.conversations(
                token,
                perPage,
                page,
                courseId
            )
        }
    }

    override suspend fun messages(
        token: String,
        uuid: String
    ): Either<Failure, List<MessageResponse>> {
        return networkHandler.callServiceBaseList {
            api.messages(
                token,
                uuid,
            )
        }
    }

    override suspend fun sendMessage(
        token: String,
        body: SendMessageRequest
    ): Either<Failure, MessageResponse> {
        return networkHandler.callServiceBase {
            api.sendMessage(
                token,
                body
            )
        }
    }

    override suspend fun sendConversation(
        token: String,
        body: SendConversationRequest
    ): Either<Failure, ConversationResponse> {
        return networkHandler.callServiceBase {
            api.sendConversation(
                token,
                body
            )
        }
    }

    override suspend fun userByCourse(
        token: String,
        name: String,
        courseId: Int,
        pagination: Boolean
    ): Either<Failure, List<UserResponse>> {
        return networkHandler.callServiceBaseList {
            api.userByCourse(
                token,
                name,
                courseId,
                pagination,
            )
        }
    }
}