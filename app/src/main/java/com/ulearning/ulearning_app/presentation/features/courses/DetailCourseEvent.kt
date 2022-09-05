package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class DetailCourseEvent : UiEvent {

    object DataDetailCourseClicked : DetailCourseEvent()

}