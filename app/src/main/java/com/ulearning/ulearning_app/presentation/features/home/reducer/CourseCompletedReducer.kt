package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CourseCompletedState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseCompletedViewState

object CourseCompletedReducer {

    private lateinit var viewState: CourseCompletedViewState

    fun instance(viewState: CourseCompletedViewState) {
        CourseCompletedReducer.viewState = viewState
    }

    fun selectState(state: CourseCompletedState) {
        when (state) {
            is CourseCompletedState.Idle -> {}

            is CourseCompletedState.Loading -> viewState.loading()

            is CourseCompletedState.CourseCompleted -> {
                viewState.getCourseCompleted(courses = state.courses)
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
