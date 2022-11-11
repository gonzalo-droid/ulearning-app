package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.CoursePercentageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CourseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse

interface CourseService {

    suspend fun subscriptions(
        token: String,
        perPage: Int,
        page: Int,
        isFinished: Boolean,
    ): Either<Failure, List<SubscriptionResponse>>

    suspend fun coursesTeacher(
        token: String,
        userId: Int,
    ): Either<Failure, List<CourseResponse>>


    suspend fun coursePercentage(
        token: String,
        courseIds: String,
    ): Either<Failure, List<CoursePercentageResponse>>
}
