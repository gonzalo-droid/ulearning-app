package com.ulearning.ulearning_app.domain.useCase.conversation

import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetParticipantsMessageUseCase
    @Inject
    constructor(private val conversationRepository: ConversationRepository) :
    BaseUseCase<List<User>, GetParticipantsMessageUseCase.Params>() {
        override suspend fun run(params: Params) =
            conversationRepository.participantsMessage(
                params.userIds,
            )

        data class Params(
            val userIds: String,
        )
    }
