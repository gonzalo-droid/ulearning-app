package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private  val courseRepository: CourseRepository
){

}