package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class HomeState : UiState {

    object Idle : HomeState()

    object Loading : HomeState()

    data class CourseList constructor(val courses: List<Subscription>) : HomeState()

    data class CourseRecentlyList constructor(val courses: List<Subscription>) : HomeState()
}