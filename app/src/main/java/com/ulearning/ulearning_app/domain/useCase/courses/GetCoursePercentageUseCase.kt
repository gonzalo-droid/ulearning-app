package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCoursePercentageUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<List<CoursePercentage>, GetCoursePercentageUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.getCoursePercentage(params.courseIds)

        data class Params(val courseIds: String)
    }
