package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.CheckAvailableFiles
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetCheckAvailableFilesUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<CheckAvailableFiles, GetCheckAvailableFilesUseCase.Params>() {

    override suspend fun run(params: Params) =
        courseRepository.checkAvailableFiles(params.subscriptionId)

    data class Params(val subscriptionId: Int)
}