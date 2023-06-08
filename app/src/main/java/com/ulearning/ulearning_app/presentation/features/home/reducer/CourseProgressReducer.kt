package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.state.CourseProgressState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseProgressViewState
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect


object CourseProgressReducer {

    private lateinit var viewState: CourseProgressViewState

    fun instance(viewState: CourseProgressViewState) {
        CourseProgressReducer.viewState = viewState
    }

    fun selectState(state: CourseProgressState) {
        when (state) {
            is CourseProgressState.Idle -> {}

            is CourseProgressState.Loading -> viewState.loading()

            is CourseProgressState.CourseRecent -> {
                viewState.getCourseRecent(courses = state.courses, percentages = state.percentages)
            }

            else -> {}
        }
    }

    fun selectEffect(effect: HomeEffect) {
        when (effect) {
            is HomeEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            else -> {}
        }
    }
}