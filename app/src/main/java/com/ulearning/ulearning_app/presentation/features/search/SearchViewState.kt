package com.ulearning.ulearning_app.presentation.features.search

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.model.User

interface SearchViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun users(users: List<User>)

}