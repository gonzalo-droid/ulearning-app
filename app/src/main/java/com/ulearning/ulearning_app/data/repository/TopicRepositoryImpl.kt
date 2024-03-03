package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.TopicMapper
import com.ulearning.ulearning_app.data.remote.service.TopicService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.domain.repository.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl
@Inject constructor(
    private val service: TopicService,
    private val mapper: TopicMapper,
    private val dataStore: DataStoreConfig
) : TopicRepository {

    override suspend fun getTopics(courseId: Int): Either<Failure, List<Topic>> {
        return when (
            val response = service.topics(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                courseId = courseId,
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listTopicToDomain(response.b))
            }
            is Either.Left -> Either.Left(response.a)
        }
    }
}
