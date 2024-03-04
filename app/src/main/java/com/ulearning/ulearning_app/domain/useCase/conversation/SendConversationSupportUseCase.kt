package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class SendConversationSupportUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<Conversation, SendConversationSupportUseCase.Params>() {
        override suspend fun run(params: Params) =
            conversationRepository.sendConversationSupport(
                params.content,
                params.toSupport,
                params.userIds,
            )

        data class Params(
            val content: String,
            val toSupport: Boolean,
            val userIds: ArrayList<Int>,
        )
    }
