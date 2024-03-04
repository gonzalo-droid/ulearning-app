package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetMyCertificateUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<FileItem, GetMyCertificateUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.myCertificates(params.subscriptionId)

        data class Params(val subscriptionId: Int)
    }
