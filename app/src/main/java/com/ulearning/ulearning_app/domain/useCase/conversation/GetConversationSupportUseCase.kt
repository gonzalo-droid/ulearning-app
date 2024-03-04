package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetConversationSupportUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<Conversation>, GetConversationSupportUseCase.Params>() {
        override suspend fun run(params: Params) = conversationRepository.getConversationsSupport(params.page, params.toSupport)

        data class Params(val page: Int, val toSupport: Boolean)
    }
