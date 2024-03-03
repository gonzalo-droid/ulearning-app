package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class MessageEvent : UiEvent {

    object MessagesClicked : MessageEvent()

    object SendMessageClick : MessageEvent()

    object GetParticipantsClick : MessageEvent()

    object GetUserIdClick : MessageEvent()
}
