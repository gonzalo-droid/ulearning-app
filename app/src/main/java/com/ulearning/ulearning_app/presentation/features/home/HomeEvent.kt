package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class HomeEvent : UiEvent {

    object CourseRecentClicked : HomeEvent()

    object CourseTeacherClicked : HomeEvent()

    object CourseCompleteClicked : HomeEvent()

    object DataProfileClicked : HomeEvent()

}