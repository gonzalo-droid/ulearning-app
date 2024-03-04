package com.ulearning.ulearning_app.presentation.features.home.event

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class CourseProgressEvent : UiEvent {
    object CourseRecentClicked : CourseProgressEvent()

    object CourseTeacherClicked : CourseProgressEvent()
}
