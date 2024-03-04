package com.ulearning.ulearning_app.presentation.features.support

import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class SupportState : UiState {
    object Idle : SupportState()

    object Loading : SupportState()

    data class Conversations constructor(val conversations: List<Conversation>) : SupportState()
}
