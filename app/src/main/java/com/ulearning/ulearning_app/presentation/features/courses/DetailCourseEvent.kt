package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class DetailCourseEvent : UiEvent {

    object DataDetailCourseClicked : DetailCourseEvent()

    object GetTopic : DetailCourseEvent()

    object SendComment : DetailCourseEvent()

    object GoToConversation : DetailCourseEvent()

    object GetToken : DetailCourseEvent()

    object GetRole : DetailCourseEvent()

}