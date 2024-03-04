package com.ulearning.ulearning_app.presentation.features.home.reducer

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackageState
import com.ulearning.ulearning_app.presentation.features.home.viewState.DetailCoursesPackageViewState

object DetailCoursesPackageReducer {
    private lateinit var viewState: DetailCoursesPackageViewState

    fun instance(viewState: DetailCoursesPackageViewState) {
        DetailCoursesPackageReducer.viewState = viewState
    }

    fun selectState(state: CoursePackageState) {
        when (state) {
            is CoursePackageState.Idle -> {}

            is CoursePackageState.Loading -> viewState.loading()

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
