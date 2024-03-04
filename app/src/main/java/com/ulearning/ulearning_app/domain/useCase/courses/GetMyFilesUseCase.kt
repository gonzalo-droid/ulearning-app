package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetMyFilesUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<List<FileItem>, GetMyFilesUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.myFiles(params.subscriptionId)

        data class Params(val subscriptionId: Int)
    }
