package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCourseUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<List<Course>, GetCourseUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.getCoursesTeacher(params.userId)

        data class Params(val userId: Int)
    }
