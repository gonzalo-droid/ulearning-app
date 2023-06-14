package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Profile

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getProfile(data: Profile)

    fun getCourseTeacher(courses: List<Course>)
}
