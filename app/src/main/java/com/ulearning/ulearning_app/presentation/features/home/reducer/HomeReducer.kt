package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.HomeState
import com.ulearning.ulearning_app.presentation.features.home.viewState.HomeViewState

object HomeReducer {
    private lateinit var viewState: HomeViewState

    fun instance(viewState: HomeViewState) {
        HomeReducer.viewState = viewState
    }

    fun selectState(state: HomeState) {
        when (state) {
            is HomeState.Idle -> {}

            is HomeState.Loading -> viewState.loading()

            is HomeState.DatProfile -> {
                viewState.getProfile(data = state.data)
            }
            is HomeState.CourseTeacher -> {
                viewState.getCourseTeacher(courses = state.courses)
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
