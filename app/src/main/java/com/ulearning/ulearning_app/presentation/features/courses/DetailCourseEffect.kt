package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class DetailCourseEffect : UiEffect {

    object ShowSuccess : DetailCourseEffect()

    object Logout : DetailCourseEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : DetailCourseEffect()
}