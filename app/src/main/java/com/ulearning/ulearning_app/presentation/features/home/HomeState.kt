package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState
import com.ulearning.ulearning_app.presentation.features.profile.ProfileState

sealed class HomeState : UiState {

    object Idle : HomeState()

    object Loading : HomeState()

    data class CourseList constructor(val courses: List<Subscription>) : HomeState()

    data class CourseRecentlyList constructor(val courses: List<Subscription>) : HomeState()

    data class DatProfile constructor(val data: Profile) : HomeState()
}