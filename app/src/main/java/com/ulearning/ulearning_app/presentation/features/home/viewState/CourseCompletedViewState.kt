package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseCompletedViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getCourseCompleted(courses: List<Subscription>)

}