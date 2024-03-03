package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CoursePackViewState

object CoursePackReducer {

    private lateinit var viewState: CoursePackViewState

    fun instance(viewState: CoursePackViewState) {
        CoursePackReducer.viewState = viewState
    }

    fun selectState(state: CoursePackState) {
        when (state) {
            is CoursePackState.Idle -> {}

            is CoursePackState.Loading -> viewState.loading()

            is CoursePackState.CoursePack -> {
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
