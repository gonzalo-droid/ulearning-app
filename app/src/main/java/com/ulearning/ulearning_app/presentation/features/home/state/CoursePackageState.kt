package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CoursePackageState : UiState {

    object Idle : CoursePackageState()

    object Loading : CoursePackageState()

    data class CoursePackage constructor(val courses: List<Subscription>) : CoursePackageState()

    data class ListCoursesPackage constructor(val courses: List<Subscription>,
                                              val percentages: List<CoursePercentage>) : CoursePackageState()

}