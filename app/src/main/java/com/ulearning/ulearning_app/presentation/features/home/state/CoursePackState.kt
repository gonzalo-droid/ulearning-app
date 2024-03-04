package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CoursePackState : UiState {
    object Idle : CoursePackState()

    object Loading : CoursePackState()

    data class CoursePack constructor(val courses: List<Subscription>) : CoursePackState()
}
