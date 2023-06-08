package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseCompleteViewState
import com.ulearning.ulearning_app.presentation.features.home.state.CourseCompletedState
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect


object CourseCompletedReducer {

    private lateinit var viewState: CourseCompleteViewState

    fun instance(viewState: CourseCompleteViewState) {
        CourseCompletedReducer.viewState = viewState
    }

    fun selectState(state: CourseCompletedState) {
        when (state) {
            is CourseCompletedState.Idle -> {}

            is CourseCompletedState.Loading -> viewState.loading()

            is CourseCompletedState.CourseComplete -> {
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