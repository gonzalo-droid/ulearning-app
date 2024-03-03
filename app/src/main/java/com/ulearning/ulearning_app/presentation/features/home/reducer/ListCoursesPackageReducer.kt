package com.ulearning.ulearning_app.presentation.features.home.reducer

import android.util.Log
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackageState
import com.ulearning.ulearning_app.presentation.features.home.viewState.ListCoursesPackageViewState

object ListCoursesPackageReducer {

    private lateinit var viewState: ListCoursesPackageViewState

    fun instance(viewState: ListCoursesPackageViewState) {
        ListCoursesPackageReducer.viewState = viewState
    }

    fun selectState(state: CoursePackageState) {
        when (state) {
            is CoursePackageState.Idle -> {}

            is CoursePackageState.Loading -> viewState.loading()

            is CoursePackageState.ListCoursesPackage -> {
                Log.d("TagItems", "ListCoursesPackage " + state.items?.size.toString())
                viewState.getListCoursesPackage(items = state.items)
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
