package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic

interface DetailCourseViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getTopics(topics: List<Topic>)

    fun goToConversation(courseId: Int)
}