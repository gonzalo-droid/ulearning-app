package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun detailCourseActivity()

    fun courseList(courses : List<Subscription>)

    fun courseRecentlyList(courses : List<Subscription>)


}