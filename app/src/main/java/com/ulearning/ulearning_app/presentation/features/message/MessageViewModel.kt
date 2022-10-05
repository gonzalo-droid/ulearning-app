package com.ulearning.ulearning_app.presentation.features.message

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.useCase.conversation.GetConversationUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.GetMessageUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel
@Inject constructor(
    private val getMessageUseCase: GetMessageUseCase
) : BaseViewModel<MessageEvent, MessageState, MessageEffect>() {

    var page: Int = 1

    var message = MutableStateFlow<String>("")

    lateinit var conversation: Conversation

    override fun createInitialState(): MessageState {
        return MessageState.Idle
    }

    override fun handleEvent(event: MessageEvent) {
        when (event) {
            MessageEvent.MessagesClicked -> getMessages()
            MessageEvent.SendMessageClick -> sendMessage()
        }
    }

    private fun sendMessage() {

        if(message.value.isNotEmpty()){

        }

    }


    private fun getMessages() {
        setState { MessageState.Loading }
        getMessageUseCase(
            GetMessageUseCase.Params(
                uuid = conversation.uuid
            )
        ) {
            it.either(::handleFailure, ::handleMessages)
        }
    }

    private fun handleMessages(messages: List<Message>) {
        setState { MessageState.Messages(messages = messages) }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { MessageEffect.ShowMessageFailure(failure = failure) }
    }

    companion object Events {
        val sendMessageClick = MessageEvent.SendMessageClick
    }
}