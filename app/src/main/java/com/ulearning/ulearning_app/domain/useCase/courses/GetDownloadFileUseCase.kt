package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetDownloadFileUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<DownloadFile, GetDownloadFileUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.downloadFile(params.hash)

        data class Params(val hash: String)
    }
