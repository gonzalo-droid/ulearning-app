package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.domain.model.Conversation


interface ConversationMapper {

    suspend fun conversationsToDomain(data: List<ConversationResponse>): List<Conversation>

}