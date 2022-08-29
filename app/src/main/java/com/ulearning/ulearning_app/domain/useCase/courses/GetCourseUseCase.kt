package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCourseUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<List<Subscription>, GetCourseUseCase.Params>() {

    override suspend fun run(params: Params) = courseRepository.getSubscriptions(params.page, params.isFinished)

    data class Params(val page: Int, val isFinished: Boolean)
}