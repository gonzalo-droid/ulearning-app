package com.ulearning.ulearning_app.presentation.features.home.viewState

import com.ulearning.ulearning_app.core.functional.Failure

interface DetailCoursesPackageViewState {

    fun messageFailure(failure: Failure)

    fun loading()
}
