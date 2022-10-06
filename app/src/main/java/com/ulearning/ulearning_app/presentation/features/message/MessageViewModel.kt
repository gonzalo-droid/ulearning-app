package com.ulearning.ulearning_app.presentation.features.message


import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.domain.useCase.conversation.GetMessageUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.SendMessageUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel
@Inject constructor(
    private val getMessageUseCase: GetMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : BaseViewModel<MessageEvent, MessageState, MessageEffect>() {

    var page: Int = 1

    var messageInput = MutableStateFlow<String>("")

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

        val ids = conversation.firstMessage?.userIds

        if (messageInput.value.isNotEmpty()) {

            sendMessageUseCase(

                SendMessageUseCase.Params(
                    uuid = conversation.uuid,
                    content = messageInput.value.trim(),
                    userIds = ids as ArrayList<String>,
                    toSupport = false

                )
            ) {
                it.either(::handleFailure, ::handleMessage)
            }
        }

    }

    private fun getMessages() {
        setState { MessageState.Loading }
        Log.d("MessageVM", conversation.uuid)
        getMessageUseCase(
            GetMessageUseCase.Params(
                uuid = conversation.uuid
            )
        ) {
            it.either(::handleFailure, ::handleMessages)
        }
    }

    private fun handleMessage(message: Message) {
        messageInput.value = ""

        Handler(Looper.getMainLooper()).postDelayed({
            getMessages()
        }, 2000)


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