package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class SendMessageUseCase
@Inject constructor(private val conversationRepository: ConversationRepository) :

    BaseUseCase<Message, SendMessageUseCase.Params>() {

    override suspend fun run(params: Params) =
        conversationRepository.sendMessages(
            params.uuid,
            params.content,
            params.userIds,
            params.toSupport
        )

    data class Params(
        val uuid: String,
        val content: String,
        val userIds: ArrayList<String>,
        val toSupport: Boolean
    )
}
