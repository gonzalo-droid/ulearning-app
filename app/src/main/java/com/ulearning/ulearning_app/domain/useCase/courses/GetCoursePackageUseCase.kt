package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCoursePackageUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<CoursePackage, GetCoursePackageUseCase.Params>() {

    override suspend fun run(params: Params) =
        courseRepository.getLearningPackage(params.learningPackageId)

    data class Params(val learningPackageId: Int)
}