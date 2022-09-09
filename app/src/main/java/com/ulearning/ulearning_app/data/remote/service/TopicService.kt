package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse

interface TopicService {

    suspend fun topics(
        token: String,
        courseId: Int,
    ): Either<Failure, List<TopicResponse>>

}
