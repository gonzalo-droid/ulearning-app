package com.ulearning.ulearning_app.presentation.features.topic

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class TopicEvent : UiEvent {

    object GoToTopic : TopicEvent()

}