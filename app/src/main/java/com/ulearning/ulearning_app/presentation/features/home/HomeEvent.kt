package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class HomeEvent : UiEvent {

    object GoToCoursesClicked : HomeEvent()

    object CoursesHomeClicked : HomeEvent()

    object RecentlyCoursesHomeClicked : HomeEvent()

}