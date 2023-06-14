package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CourseRouteState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseRouteViewState

object CourseRouteReducer {

    private lateinit var viewState: CourseRouteViewState

    fun instance(viewState: CourseRouteViewState) {
        CourseRouteReducer.viewState = viewState
    }

    fun selectState(state: CourseRouteState) {
        when (state) {
            is CourseRouteState.Idle -> {}

            is CourseRouteState.Loading -> viewState.loading()

            is CourseRouteState.CourseRoute -> {
                viewState.getCourseRoute(courses = state.courses)
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
