package com.ulearning.ulearning_app.presentation.features.support

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class SupportEvent : UiEvent {

    object ConversationsClicked : SupportEvent()

    object AddConversationClick : SupportEvent()


}