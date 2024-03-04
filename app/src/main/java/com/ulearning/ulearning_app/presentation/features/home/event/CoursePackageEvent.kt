package com.ulearning.ulearning_app.presentation.features.home.event

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class CoursePackageEvent : UiEvent {
    object CoursePackageClicked : CoursePackageEvent()

    object ListCoursesPackageClicked : CoursePackageEvent()
}
