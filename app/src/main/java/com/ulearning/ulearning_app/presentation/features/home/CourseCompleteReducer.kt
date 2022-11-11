package com.ulearning.ulearning_app.presentation.features.home


object CourseCompleteReducer {

    private lateinit var viewState: CourseCompleteViewState

    fun instance(viewState: CourseCompleteViewState) {
        this.viewState = viewState
    }

    fun selectState(state: HomeState) {
        when (state) {
            is HomeState.Idle -> {}

            is HomeState.Loading -> viewState.loading()

            is HomeState.CourseComplete -> {
                viewState.getCourseComplete(courses = state.courses)
            }
        }
    }

    fun selectEffect(effect: HomeEffect) {
        when (effect) {
            is HomeEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            else -> {}
        }
    }
}