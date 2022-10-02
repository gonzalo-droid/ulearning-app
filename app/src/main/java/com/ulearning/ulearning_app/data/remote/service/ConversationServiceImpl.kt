package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.ConversationApi
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
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
}