package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User

interface ConversationRepository {

    suspend fun getConversations(
        page: Int,
        courseId: Int
    ): Either<Failure, List<Conversation>>

    suspend fun getConversationsSupport(
        page: Int,
    ): Either<Failure, List<Conversation>>

    suspend fun getMessages(
        uuid: String
    ): Either<Failure, List<Message>>

    suspend fun sendMessages(
        uuid: String,
        content: String,
        userIds: ArrayList<String>,
        toSupport: Boolean
    ): Either<Failure, Message>

    suspend fun sendConversation(
        content: String,
        courseId: Int,
        userIds: ArrayList<Int>,
    ): Either<Failure, Conversation>

    suspend fun sendConversationSupport(
        content: String,
        toSupport: Boolean,
        userIds: ArrayList<Int>,
    ): Either<Failure, Conversation>


    suspend fun getUserByCourse(
        name: String,
        courseId: Int,
    ): Either<Failure, List<User>>

    suspend fun getUserByIds(
        ids: String,
        courseId: Int,
    ): Either<Failure, List<User>>
}