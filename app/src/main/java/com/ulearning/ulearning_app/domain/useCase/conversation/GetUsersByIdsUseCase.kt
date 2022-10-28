package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetUsersByIdsUseCase
@Inject constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<User>, GetUsersByIdsUseCase.Params>() {

    override suspend fun run(params: Params) =
        conversationRepository.getUserByIds(params.ids, params.courseId)

    data class Params(val ids: String, val courseId: Int)
}
