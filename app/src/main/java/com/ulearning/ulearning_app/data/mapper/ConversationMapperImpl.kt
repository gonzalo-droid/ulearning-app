package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.FirstMessageResponse
import com.ulearning.ulearning_app.domain.model.Conversation
import javax.inject.Singleton

@Singleton
class ConversationMapperImpl : ConversationMapper {

    override suspend fun conversationsToDomain(data: List<ConversationResponse>): List<Conversation> {
        return data.map {
            Conversation(
                canByReply = it.canByReply,
                courseId = it.courseId,
                firstMessage = FirstMessageResponse(
                    classification = it.firstMessage?.classification,
                    content = it.firstMessage?.content,
                    id = it.firstMessage?.id,
                    parentId = it.firstMessage?.parentId,
                    publishedAt = it.firstMessage?.publishedAt,
                    sendBy = it.firstMessage?.sendBy,
                    status = it.firstMessage?.status,
                    subject = it.firstMessage?.subject,
                    type = it.firstMessage?.type,
                    userIds = it.firstMessage?.userIds,
                    uuid = it.firstMessage?.uuid,
                ),
                id = it.courseId,
                isBroadcast = it.isBroadcast,
                replyToAuthor = it.replyToAuthor,
                toSupport = it.toSupport,
                uuid = it.uuid
            )
        }
    }
}