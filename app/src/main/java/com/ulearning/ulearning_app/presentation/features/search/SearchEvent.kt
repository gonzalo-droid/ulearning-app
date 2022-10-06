package com.ulearning.ulearning_app.presentation.features.search

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class SearchEvent : UiEvent {

    object SendConversationClick : SearchEvent()
}