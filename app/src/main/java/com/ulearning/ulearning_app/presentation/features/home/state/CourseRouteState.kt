package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CourseRouteState : UiState {

    object Idle : CourseRouteState()

    object Loading : CourseRouteState()

    data class CourseRoute constructor(val courses: List<Subscription>) : CourseRouteState()
}
