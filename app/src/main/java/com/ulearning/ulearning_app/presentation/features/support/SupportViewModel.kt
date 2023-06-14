package com.ulearning.ulearning_app.presentation.features.support

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.useCase.conversation.GetConversationSupportUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportViewModel
@Inject constructor(
    private val getConversationSupportUseCase: GetConversationSupportUseCase
) : BaseViewModel<SupportEvent, SupportState, SupportEffect>() {

    var page: Int = 1
    var courseId: Int = 0

    override fun createInitialState(): SupportState {
        return SupportState.Idle
    }

    override fun handleEvent(event: SupportEvent) {
        when (event) {
            SupportEvent.AddConversationClick -> addConversation()
            SupportEvent.ConversationsClicked -> getConversations()
        }
    }

    private fun addConversation() {
        setEffect { SupportEffect.GoToNewConversation }
    }

    private fun getConversations() {
        setState { SupportState.Loading }
        getConversationSupportUseCase(
            GetConversationSupportUseCase.Params(
                page = page,
            )
        ) {
            it.either(::handleFailure, ::handleConversations)
        }
    }

    private fun handleConversations(conversations: List<Conversation>) {
        setState { SupportState.Conversations(conversations = conversations) }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { SupportEffect.ShowMessageFailure(failure = failure) }
    }

    companion object Events {
        val addConversationClick = SupportEvent.AddConversationClick
    }
}
