package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetMessageUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<Message>, GetMessageUseCase.Params>() {
        override suspend fun run(params: Params) = conversationRepository.getMessages(params.uuid)

        data class Params(val uuid: String)
    }
