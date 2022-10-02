package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation

interface ConversationRepository {

    suspend fun getConversations(
        page: Int,
        courseId: Int
    ): Either<Failure, List<Conversation>>

}