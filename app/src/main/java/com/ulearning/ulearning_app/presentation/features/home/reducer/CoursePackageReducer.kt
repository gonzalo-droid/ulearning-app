package com.ulearning.ulearning_app.presentation.features.home.reducer

import android.util.Log
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackageState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CoursePackageViewState

object CoursePackageReducer {
    private lateinit var viewState: CoursePackageViewState

    fun instance(viewState: CoursePackageViewState) {
        CoursePackageReducer.viewState = viewState
    }

    fun selectState(state: CoursePackageState) {
        when (state) {
            is CoursePackageState.Idle -> {}

            is CoursePackageState.Loading -> viewState.loading()

            is CoursePackageState.CoursePackageData -> {
                viewState.getCoursePackage(course = state.course!!, percentages = state.percentages)
            }

            else -> {
                Log.d("TagItems", "ListCoursesPackage CoursePackageReducer")
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
