package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription

interface CoursePackageViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getCoursePackage(course: CoursePackage)

}
