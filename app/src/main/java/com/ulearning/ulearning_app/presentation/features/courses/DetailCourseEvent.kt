package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class DetailCourseEvent : UiEvent {

    object DataDetailCourseClicked : DetailCourseEvent()

    object GetTopic : DetailCourseEvent()

    object SendComment : DetailCourseEvent()

    object GoToConversation : DetailCourseEvent()

    object GoToCertificate : DetailCourseEvent()

    object GoToRecord : DetailCourseEvent()

    object GetToken : DetailCourseEvent()

    object GetRole : DetailCourseEvent()

    object GetMyFiles : DetailCourseEvent()

    object GetCheckAvailableFiles : DetailCourseEvent()

    object GetDownloadFile : DetailCourseEvent()

}