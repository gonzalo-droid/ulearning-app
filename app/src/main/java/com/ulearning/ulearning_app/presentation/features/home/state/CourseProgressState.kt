package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class CourseProgressState : UiState {

    object Idle : CourseProgressState()

    object Loading : CourseProgressState()

    data class CourseTeacher constructor(val courses: List<Course>) : CourseProgressState()

    data class CourseRecent constructor(val courses: List<Subscription>, val percentages: List<CoursePercentage>) : CourseProgressState()
}
