package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetDownloadShowGuestFileUseCase
@Inject constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<DownloadFile, GetDownloadShowGuestFileUseCase.Params>() {

    override suspend fun run(params: Params) =
        courseRepository.downloadGuestFile(params.hash, params.name)

    data class Params(val hash: String, val name: String)
}
