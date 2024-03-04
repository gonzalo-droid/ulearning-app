package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetUserByCourseUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<User>, GetUserByCourseUseCase.Params>() {
        override suspend fun run(params: Params) = conversationRepository.getUserByCourse(params.name, params.courseId)

        data class Params(val name: String, val courseId: Int)
    }
