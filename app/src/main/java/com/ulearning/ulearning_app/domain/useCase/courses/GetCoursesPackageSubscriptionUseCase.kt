package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCoursesPackageSubscriptionUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<List<Subscription>, GetCoursesPackageSubscriptionUseCase.Params>() {

    override suspend fun run(params: Params) = courseRepository.getSubscriptionsPackage(
        params.page,
        params.type,
    )

    data class Params(val page: Int, val type: String)
}
