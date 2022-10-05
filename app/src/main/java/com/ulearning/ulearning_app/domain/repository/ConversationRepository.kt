package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message

interface ConversationRepository {

    suspend fun getConversations(
        page: Int,
        courseId: Int
    ): Either<Failure, List<Conversation>>

    suspend fun getMessages(
        uuid: String
    ): Either<Failure, List<Message>>

    suspend fun sendMessages(
        uuid: String,
        content: String,
        userIds: ArrayList<String>,
    ): Either<Failure, Message>

}