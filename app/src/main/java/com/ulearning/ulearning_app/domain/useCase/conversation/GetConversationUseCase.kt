package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetConversationUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<Conversation>, GetConversationUseCase.Params>() {
        override suspend fun run(params: Params) = conversationRepository.getConversations(params.page, params.courseId)

        data class Params(val page: Int, val courseId: Int)
    }
