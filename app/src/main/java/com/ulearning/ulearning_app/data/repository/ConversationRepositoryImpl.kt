package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.ConversationMapper
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationSupportRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendMessageRequest
import com.ulearning.ulearning_app.data.remote.service.ConversationService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import javax.inject.Inject

class ConversationRepositoryImpl
@Inject constructor(
    private val service: ConversationService,
    private val mapper: ConversationMapper,
    private val dataStore: DataStoreConfig,
) : ConversationRepository {

    override suspend fun getConversations(
        page: Int,
        courseId: Int,
    ): Either<Failure, List<Conversation>> {
        return when (
            val response = service.conversations(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                perPage = SettingRemote.PER_PAGE,
                page = page,
                courseId = courseId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.conversationsToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getConversationsSupport(page: Int): Either<Failure, List<Conversation>> {
        return when (
            val response = service.conversationsSupport(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                perPage = SettingRemote.PER_PAGE,
                page = page,
                toSupport = true
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.conversationsToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun participantsMessage(ids: String): Either<Failure, List<User>> {
        return when (
            val response = service.participantsMessage(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                ids = ids
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.usersToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getMessages(uuid: String): Either<Failure, List<Message>> {
        return when (
            val response = service.messages(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                uuid = uuid
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.messagesToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun sendMessages(
        uuid: String,
        content: String,
        userIds: ArrayList<String>,
        toSupport: Boolean,
    ): Either<Failure, Message> {
        return when (
            val response = service.sendMessage(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                body = SendMessageRequest(
                    uuid = uuid,
                    content = content,
                    userIds = userIds,
                    toSupport = toSupport
                ),
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.messageToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun sendConversation(
        content: String,
        courseId: Int,
        userIds: ArrayList<Int>,
    ): Either<Failure, Conversation> {
        return when (
            val response = service.sendConversation(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                body = SendConversationRequest(
                    content = content,
                    courseId = courseId,
                    userIds = userIds,
                ),
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.conversationToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun sendConversationSupport(
        content: String,
        toSupport: Boolean,
        userIds: ArrayList<Int>,
    ): Either<Failure, Conversation> {
        return when (
            val response = service.sendConversationSupport(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                body = SendConversationSupportRequest(
                    content = content,
                    toSupport = toSupport,
                    userIds = userIds,
                ),
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.conversationToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getUserByCourse(name: String, courseId: Int): Either<Failure, List<User>> {
        return when (
            val response = service.userByCourse(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                name = name,
                courseId = courseId,
                withoutPagination = true
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.usersToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getUserByIds(ids: String, courseId: Int): Either<Failure, List<User>> {
        return when (
            val response = service.userByIds(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                ids = ids,
                courseId = courseId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.usersToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }
}
