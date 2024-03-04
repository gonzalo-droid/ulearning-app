package com.ulearning.ulearning_app.presentation.features.home.event

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class CourseCompletedEvent : UiEvent {
    object CourseCompleteClicked : CourseCompletedEvent()
}
