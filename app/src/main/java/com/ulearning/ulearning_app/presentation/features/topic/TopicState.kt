package com.ulearning.ulearning_app.presentation.features.topic

import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class TopicState : UiState {
    object Idle : TopicState()

    object Loading : TopicState()

    data class ListTopic constructor(val topics: List<Topic>) : TopicState()
}
