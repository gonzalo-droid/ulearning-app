package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun detailCourseActivity()

    fun courseList(courses : List<Course>)

    fun courseRecentlyList(courses : List<Course>)


}