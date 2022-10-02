package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.ConversationMapper
import com.ulearning.ulearning_app.data.remote.service.ConversationService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import javax.inject.Inject

class ConversationRepositoryImpl
@Inject constructor(
    private val service: ConversationService,
    private val mapper: ConversationMapper,
    private val dataStore: DataStoreConfig
) : ConversationRepository {

    override suspend fun getConversations(
        page: Int,
        courseId: Int
    ): Either<Failure, List<Conversation>> {
        return when (val response = service.conversations(
            token = "${SettingRemote.BEARER} ${dataStore.token()}",
            perPage = SettingRemote.PER_PAGE,
            page = page,
            courseId = courseId
        )) {
            is Either.Right -> {
                Either.Right(mapper.conversationsToDomain(response.b))
            }
            is Either.Left -> Either.Left(response.a)
        }
    }
}