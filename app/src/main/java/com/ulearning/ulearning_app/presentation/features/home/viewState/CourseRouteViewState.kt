package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseRouteViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getCourseRoute(courses: List<Subscription>)
}
