package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.UiState
import com.ulearning.ulearning_app.presentation.features.topic.TopicState

sealed class DetailCourseState : UiState {

    object Idle : DetailCourseState()

    object Loading : DetailCourseState()

    data class DataDetailCourse constructor(val data: Subscription) : DetailCourseState()

    data class ListTopic constructor(val topics: List<Topic>) : DetailCourseState()


}