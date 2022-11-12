package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getCourseRecent(courses: List<Subscription>, percentages: List<CoursePercentage>)

}