package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class DetailCourseState : UiState {

    object Idle : DetailCourseState()

    object Loading : DetailCourseState()

    data class DataDetailCourse constructor(val data: Subscription) : DetailCourseState()

}