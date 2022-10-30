package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun courseList(courses : List<Course>)

    fun courseSubscriptionList(courses : List<Subscription>)

    fun courseSubscriptionRecentlyList(courses : List<Subscription>)

    fun getProfile(data: Profile)

}