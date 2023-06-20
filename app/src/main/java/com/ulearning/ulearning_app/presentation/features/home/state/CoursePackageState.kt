package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CoursePackageState : UiState {

    object Idle : CoursePackageState()

    object Loading : CoursePackageState()

    data class CoursePackageData constructor(val course: Subscription) : CoursePackageState()
    data class ListCoursesPackage constructor(
        val items: List<LearningPackageItem>?
    ) : CoursePackageState()
}
