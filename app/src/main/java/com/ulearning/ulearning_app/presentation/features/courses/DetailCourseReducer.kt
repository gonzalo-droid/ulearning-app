package com.ulearning.ulearning_app.presentation.features.courses


object DetailCourseReducer {

    private lateinit var viewState: DetailCourseViewState

    fun instance(viewState: DetailCourseViewState) {
        this.viewState = viewState
    }

    fun selectState(state: DetailCourseState) {
        when (state) {
            is DetailCourseState.Idle -> {}

            is DetailCourseState.Loading -> viewState.loading()

            is DetailCourseState.DataDetailCourse -> {}

            is DetailCourseState.ListTopic -> {
                viewState.getTopics(topics = state.topics)
            }
        }
    }

    fun selectEffect(effect: DetailCourseEffect) {
        when (effect) {
            is DetailCourseEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is DetailCourseEffect.GoToConversation -> viewState.goToConversation(effect.courseId)
        }
    }
}