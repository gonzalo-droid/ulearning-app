package com.ulearning.ulearning_app.presentation.features.topic

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.domain.useCase.topic.GetTopicUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicViewModel
    @Inject
    constructor(
        private val getTopicUseCase: GetTopicUseCase,
    ) : BaseViewModel<TopicEvent, TopicState, TopicEffect>() {
        var courseId = 0

        override fun createInitialState(): TopicState {
            return TopicState.Idle
        }

        override fun handleEvent(event: TopicEvent) {
            when (event) {
                TopicEvent.GoToTopic -> getTopic()
            }
        }

        private fun getTopic() {
            setState { TopicState.Loading }

            getTopicUseCase(
                GetTopicUseCase.Params(courseId = courseId),
            ) {
                it.either(::handleFailure, ::handleTopics)
            }
        }

        private fun handleFailure(failure: Failure) {
            setEffect { TopicEffect.ShowMessageFailure(failure = failure) }
        }

        private fun handleTopics(topics: List<Topic>) {
            setState { TopicState.ListTopic(topics = topics) }
        }

        companion object Events {
            val goToTopic = TopicEvent.GoToTopic
        }
    }
