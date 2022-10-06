package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.UserResponse
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User


interface ConversationMapper {

    suspend fun conversationsToDomain(data: List<ConversationResponse>): List<Conversation>

    suspend fun messagesToDomain(data: List<MessageResponse>): List<Message>

    suspend fun usersToDomain(data: List<UserResponse>): List<User>

    suspend fun messageToDomain(data: MessageResponse): Message

    suspend fun conversationToDomain(data: ConversationResponse): Conversation

}