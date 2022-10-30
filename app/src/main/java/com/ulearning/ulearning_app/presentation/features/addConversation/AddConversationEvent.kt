package com.ulearning.ulearning_app.presentation.features.addConversation

import com.ulearning.ulearning_app.presentation.base.UiEvent
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseEvent


sealed class AddConversationEvent : UiEvent {

    object SendConversationClick : AddConversationEvent()

    object SendConversationSupportClick : AddConversationEvent()

    object GetUsersClick : AddConversationEvent()

    object GetRole : AddConversationEvent()

}