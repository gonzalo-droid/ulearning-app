package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseCompleteViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getCourseComplete(courses: List<Subscription>)

}