package com.ulearning.ulearning_app.presentation.features.topic

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic

interface TopicViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getTopics(topics : List<Topic>)
}