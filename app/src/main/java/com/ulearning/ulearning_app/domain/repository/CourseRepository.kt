package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.User

interface CourseRepository {

    suspend fun getSubscriptions(
        page: Int,
        isFinished: Boolean
    ): Either<Failure, List<Subscription>>

    suspend fun getCoursesTeacher(
        userId: Int
    ): Either<Failure, List<Course>>

    suspend fun getCoursePercentage(
        courseIds: String
    ): Either<Failure, List<CoursePercentage>>
}
