package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Topic

interface TopicRepository {

    suspend fun getTopics(courseId: Int): Either<Failure, List<Topic>>
}
