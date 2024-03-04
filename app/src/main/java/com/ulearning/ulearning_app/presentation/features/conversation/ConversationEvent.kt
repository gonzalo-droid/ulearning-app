package com.ulearning.ulearning_app.presentation.features.conversation

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class ConversationEvent : UiEvent {
    object ConversationsClicked : ConversationEvent()

    object AddConversationClick : ConversationEvent()
}
