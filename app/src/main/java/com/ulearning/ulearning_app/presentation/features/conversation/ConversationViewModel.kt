package com.ulearning.ulearning_app.presentation.features.conversation

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.useCase.conversation.GetConversationUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel
@Inject constructor(
    private val getConversationUseCase: GetConversationUseCase
) : BaseViewModel<ConversationEvent, ConversationState, ConversationEffect>() {

    var page: Int = 1
    var courseId: Int = 0

    override fun createInitialState(): ConversationState {
        return ConversationState.Idle
    }

    override fun handleEvent(event: ConversationEvent) {
        when (event) {
            ConversationEvent.ConversationDetailClicked -> goToMessage()
            ConversationEvent.AddConversationClick -> addConversation()
            ConversationEvent.ConversationsClicked -> getConversations()
        }
    }

    private fun goToMessage() {

    }

    private fun addConversation() {

    }

    private fun getConversations() {
        setState { ConversationState.Loading }
        getConversationUseCase(GetConversationUseCase.Params(
            page = page,
            courseId = courseId
        )) {
            it.either(::handleFailure, ::handleConversations)
        }
    }

    private fun handleConversations(conversations: List<Conversation>) {
        setState { ConversationState.Conversations(conversations = conversations) }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { ConversationEffect.ShowMessageFailure(failure = failure) }
    }

    companion object Events {
        val conversationDetailClicked = ConversationEvent.ConversationDetailClicked
        val addConversationClick = ConversationEvent.AddConversationClick


    }
}