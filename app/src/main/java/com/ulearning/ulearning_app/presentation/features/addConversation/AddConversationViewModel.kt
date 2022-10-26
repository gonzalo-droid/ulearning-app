package com.ulearning.ulearning_app.presentation.features.addConversation


import android.os.Handler
import android.os.Looper
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.useCase.conversation.GetUserByCourseUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.SendConversationUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.SendMessageUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddConversationViewModel
@Inject constructor(
    private val getUserByCourseUseCase: GetUserByCourseUseCase,
    private val sendConversationUseCase: SendConversationUseCase,
) : BaseViewModel<AddConversationEvent, AddConversationState, AddConversationEffect>() {

    var courseId: Int = 1

    var search: String = ""

    var messageInput = MutableStateFlow<String>("")

    lateinit var user: User

    override fun createInitialState(): AddConversationState {
        return AddConversationState.Idle
    }

    override fun handleEvent(event: AddConversationEvent) {
        when (event) {
            AddConversationEvent.SendConversationClick -> sendConversation()
        }
    }

    fun searchUser(search: String) {
        getUserByCourseUseCase(
            GetUserByCourseUseCase.Params(name = search, courseId = courseId)
        ) {
            it.either(::handleFailure, ::handleUser)
        }
    }

    private fun sendConversation() {

        if (messageInput.value.isNotEmpty() && ::user.isInitialized) {

            val userIds = arrayListOf<Int>()
            user.id?.let { userIds.add(it) }
            sendConversationUseCase(
                SendConversationUseCase.Params(
                    content = messageInput.value.trim(),
                    courseId = courseId,
                    userIds = userIds
                )
            ) {
                it.either(::handleFailure, ::handleConversation)
            }
        }
    }

    private fun handleConversation(conversation: Conversation) {
        setState { AddConversationState.Loading }

        Handler(Looper.getMainLooper()).postDelayed({
            setState { AddConversationState.DataConversation(conversation = conversation) }
        }, 2000)


    }

    private fun handleUser(users: List<User>) {
        setState { AddConversationState.UserList(users = users) }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { AddConversationEffect.ShowMessageFailure(failure = failure) }
    }

    companion object Events {
        val sendConversationClick = AddConversationEvent.SendConversationClick
    }
}