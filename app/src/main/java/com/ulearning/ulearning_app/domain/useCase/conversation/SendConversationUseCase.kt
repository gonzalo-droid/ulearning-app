package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class SendConversationUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<Conversation, SendConversationUseCase.Params>() {
        override suspend fun run(params: Params) =
            conversationRepository.sendConversation(
                params.content,
                params.courseId,
                params.userIds,
            )

        data class Params(
            val content: String,
            val courseId: Int,
            val userIds: ArrayList<Int>,
        )
    }
