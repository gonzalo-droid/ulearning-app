package com.ulearning.ulearning_app.presentation.features.home


object CourseReducer {

    private lateinit var viewState: CourseViewState

    fun instance(viewState: CourseViewState) {
        this.viewState = viewState
    }

    fun selectState(state: HomeState) {
        when (state) {
            is HomeState.Idle -> {}

            is HomeState.Loading -> viewState.loading()

            is HomeState.CourseRecent -> {
                viewState.getCourseRecent(courses = state.courses)
            }
            is HomeState.CourseTeacher -> {
                viewState.getCourseTeacher(courses = state.courses)
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