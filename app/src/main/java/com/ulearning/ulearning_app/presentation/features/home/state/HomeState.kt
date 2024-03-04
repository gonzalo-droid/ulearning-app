package com.ulearning.ulearning_app.presentation.features.home.state

import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class HomeState : UiState {
    object Idle : HomeState()

    object Loading : HomeState()

    data class DatProfile constructor(val data: Profile) : HomeState()

    data class CourseTeacher constructor(val courses: List<Course>) : HomeState()

    data class CourseCompleted constructor(val courses: List<Subscription>) : HomeState()

    data class CourseRecent constructor(val courses: List<Subscription>, val percentages: List<CoursePercentage>) : HomeState()
}
