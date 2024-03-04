package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseProgressViewState {
    fun messageFailure(failure: Failure)

    fun loading()

    fun getCourseRecent(
        courses: List<Subscription>,
        percentages: List<CoursePercentage>,
    )
}
