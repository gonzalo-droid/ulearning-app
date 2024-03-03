package com.ulearning.ulearning_app.presentation.features.courseDetail

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class DetailCourseEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : DetailCourseEffect()

    data class GoToConversation constructor(val courseId: Int) : DetailCourseEffect()
}
