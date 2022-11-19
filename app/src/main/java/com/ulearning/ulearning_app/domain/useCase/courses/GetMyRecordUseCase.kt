package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetMyRecordUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<FileItem, GetMyRecordUseCase.Params>() {

    override suspend fun run(params: Params) =
        courseRepository.myRecords(params.subscriptionId)

    data class Params(val subscriptionId: Int)
}