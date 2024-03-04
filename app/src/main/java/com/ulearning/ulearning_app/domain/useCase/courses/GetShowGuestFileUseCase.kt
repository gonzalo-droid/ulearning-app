package com.ulearning.ulearning_app.domain.useCase.courses

import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetShowGuestFileUseCase
    @Inject
    constructor(private val courseRepository: CourseRepository) :
    BaseUseCase<FileItem, GetShowGuestFileUseCase.Params>() {
        override suspend fun run(params: Params) = courseRepository.showGuestFile(params.name)

        data class Params(val name: String)
    }
