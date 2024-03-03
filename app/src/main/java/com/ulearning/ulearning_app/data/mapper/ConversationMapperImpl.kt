package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.UserResponse
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.FirstMessage
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User
import javax.inject.Singleton

@Singleton
class ConversationMapperImpl : ConversationMapper {

    override suspend fun conversationsToDomain(data: List<ConversationResponse>): List<Conversation> {
        return data.map {
            Conversation(
                canByReply = it.canByReply,
                courseId = it.courseId,
                firstMessage = FirstMessage(
                    classification = it.firstMessage?.classification,
                    content = it.firstMessage?.content,
                    id = it.firstMessage?.id,
                    publishedAt = it.firstMessage?.publishedAt,
                    sendBy = it.firstMessage?.sendBy.let { user ->
                        User(
                            address = user?.address,
                            dateOfBirth = user?.dateOfBirth,
                            documentNumber = user?.documentNumber,
                            documentType = user?.documentType,
                            email = user?.email,
                            firstName = user?.firstName,
                            gender = user?.gender,
                            id = user?.id,
                            lastName = user?.lastName,
                            name = user?.name,
                            phone = user?.phone,
                            phoneCode = user?.phoneCode,
                            role = user?.role,
                            secondLastName = user?.secondLastName,
                        )
                    },
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

    override suspend fun messagesToDomain(data: List<MessageResponse>): List<Message> {
        return data.map {
            Message(
                classification = it.classification,
                content = it.content,
                id = it.id,
                publishedAt = it.publishedAt,
                sendBy = it.sendBy.let { user ->
                    User(
                        address = user?.address,
                        dateOfBirth = user?.dateOfBirth,
                        documentNumber = user?.documentNumber,
                        documentType = user?.documentType,
                        email = user?.email,
                        firstName = user?.firstName,
                        gender = user?.gender,
                        id = user?.id,
                        lastName = user?.lastName,
                        name = user?.name,
                        phone = user?.phone,
                        phoneCode = user?.phoneCode,
                        role = user?.role,
                        secondLastName = user?.secondLastName,
                    )
                },
                status = it.status,
                subject = it.subject,
                type = it.type,
                userIds = it.userIds,
                uuid = it.uuid,
            )
        }
    }

    override suspend fun usersToDomain(data: List<UserResponse>): List<User> {
        return data.map {
            User(
                address = it.address,
                dateOfBirth = it.dateOfBirth,
                documentNumber = it.documentNumber,
                documentType = it.documentType,
                email = it.email,
                firstName = it.firstName,
                gender = it.gender,
                id = it.id,
                lastName = it.lastName,
                name = it.name,
                phone = it.phone,
                phoneCode = it.phoneCode,
                role = it.role,
                secondLastName = it.secondLastName,
            )
        }
    }

    override suspend fun messageToDomain(data: MessageResponse): Message {
        return data.let {
            Message(
                classification = it.classification,
                content = it.content,
                id = it.id,
                publishedAt = it.publishedAt,
                sendBy = it.sendBy.let { user ->
                    User(
                        address = user?.address,
                        dateOfBirth = user?.dateOfBirth,
                        documentNumber = user?.documentNumber,
                        documentType = user?.documentType,
                        email = user?.email,
                        firstName = user?.firstName,
                        gender = user?.gender,
                        id = user?.id,
                        lastName = user?.lastName,
                        name = user?.name,
                        phone = user?.phone,
                        phoneCode = user?.phoneCode,
                        role = user?.role,
                        secondLastName = user?.secondLastName,
                    )
                },
                status = it.status,
                subject = it.subject,
                type = it.type,
                userIds = it.userIds,
                uuid = it.uuid,
            )
        }
    }

    override suspend fun conversationToDomain(data: ConversationResponse): Conversation {
        return data.let {
            Conversation(
                canByReply = it.canByReply,
                courseId = it.courseId,
                firstMessage = FirstMessage(
                    classification = it.firstMessage?.classification,
                    content = it.firstMessage?.content,
                    id = it.firstMessage?.id,
                    publishedAt = it.firstMessage?.publishedAt,
                    sendBy = it.firstMessage?.sendBy.let { user ->
                        User(
                            address = user?.address,
                            dateOfBirth = user?.dateOfBirth,
                            documentNumber = user?.documentNumber,
                            documentType = user?.documentType,
                            email = user?.email,
                            firstName = user?.firstName,
                            gender = user?.gender,
                            id = user?.id,
                            lastName = user?.lastName,
                            name = user?.name,
                            phone = user?.phone,
                            phoneCode = user?.phoneCode,
                            role = user?.role,
                            secondLastName = user?.secondLastName,
                        )
                    },
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
