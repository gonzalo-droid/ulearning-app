package com.ulearning.ulearning_app.domain.repository.courses

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseRepository {

    suspend fun getSubscriptions(
        page: Int,
        isFinished: Boolean
    ): Either<Failure, List<Subscription>>

}