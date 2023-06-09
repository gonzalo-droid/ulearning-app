package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CourseCompletedState : UiState {

    object Idle : CourseCompletedState()

    object Loading : CourseCompletedState()

    data class CourseCompleted constructor(val courses: List<Subscription>) : CourseCompletedState()

}