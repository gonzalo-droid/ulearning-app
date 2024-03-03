package com.ulearning.ulearning_app.presentation.features.topic

object TopicReducer {

    private lateinit var viewState: TopicViewState

    fun instance(viewState: TopicViewState) {
        this.viewState = viewState
    }

    fun selectState(state: TopicState) {
        when (state) {
            is TopicState.Idle -> {}

            is TopicState.Loading -> viewState.loading()

            is TopicState.ListTopic -> {
                viewState.getTopics(topics = state.topics)
            }
        }
    }

    fun selectEffect(effect: TopicEffect) {
        when (effect) {
            is TopicEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            else -> {}
        }
    }
}
