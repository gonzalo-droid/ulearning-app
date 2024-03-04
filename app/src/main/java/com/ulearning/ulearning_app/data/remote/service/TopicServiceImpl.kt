package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.TopicApi
import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicServiceImpl
    @Inject
    constructor(
        private val api: TopicApi,
        private val networkHandler: NetworkHandler,
    ) : TopicService {
        override suspend fun topics(
            token: String,
            courseId: Int,
        ): Either<Failure, List<TopicResponse>> {
            return networkHandler.callServiceBaseList {
                api.topics(token, courseId)
            }
        }
    }
