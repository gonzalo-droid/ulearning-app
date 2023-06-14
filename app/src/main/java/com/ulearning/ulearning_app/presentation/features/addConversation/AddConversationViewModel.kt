package com.ulearning.ulearning_app.presentation.features.addConversation

import android.os.Handler
import android.os.Looper
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetRoleUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.GetUsersByIdsUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.SendConversationSupportUseCase
import com.ulearning.ulearning_app.domain.useCase.conversation.SendConversationUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddConversationViewModel
@Inject constructor(
    private val getUsersByIdsUseCase: GetUsersByIdsUseCase,
    private val sendConversationUseCase: SendConversationUseCase,
    private val sendConversationSupportUseCase: SendConversationSupportUseCase,
    private val getRoleUseCase: GetRoleUseCase,
) : BaseViewModel<AddConversationEvent, AddConversationState, AddConversationEffect>() {

    var courseId: Int = 0

    var listUserIds: ArrayList<Int> = arrayListOf<Int>()

    var textUserIds: String = ""

    var typeMessage: String = ""

    var typeRole: String = ""

    var messageInput = MutableStateFlow<String>("")

    override fun createInitialState(): AddConversationState {
        return AddConversationState.Idle
    }

    override fun handleEvent(event: AddConversationEvent) {
        when (event) {
            AddConversationEvent.SendConversationClick -> send()
            AddConversationEvent.GetUsersClick -> getUsers()
            AddConversationEvent.GetRole -> getRole()
        }
    }

    private fun getRole() = getRoleUseCase(BaseUseCase.None()) {
        it.either(::handleFailure, ::handleRole)
    }

    private fun getUsers() {
        if (textUserIds.isNotEmpty() && courseId != 0) {
            getUsersByIdsUseCase(
                GetUsersByIdsUseCase.Params(ids = textUserIds, courseId = courseId)
            ) {
                it.either(::handleFailure, ::handleUser)
            }
        }
    }

    private fun send() {

        if (typeMessage == Config.MESSAGE_COURSE) {
            sendConversation()
        } else {
            sendConversationSupport()
        }
    }

    private fun sendConversationSupport() {

        if (messageInput.value.isNotEmpty()) {

            sendConversationSupportUseCase(
                SendConversationSupportUseCase.Params(
                    content = messageInput.value.trim(),
                    toSupport = true,
                    userIds = listUserIds
                )
            ) {
                it.either(::handleFailure, ::handleConversation)
            }
        } else {
            setEffect { AddConversationEffect.ShowMessageFailure(Failure.DefaultError(R.string.failure_message)) }
        }
    }

    private fun sendConversation() {

        if (messageInput.value.isNotEmpty() && listUserIds.isNotEmpty()) {

            sendConversationUseCase(
                SendConversationUseCase.Params(
                    content = messageInput.value.trim(),
                    courseId = courseId,
                    userIds = listUserIds
                )
            ) {
                it.either(::handleFailure, ::handleConversation)
            }
        } else {
            setEffect { AddConversationEffect.ShowMessageFailure(Failure.DefaultError(R.string.failure_message)) }
        }
    }

    private fun handleConversation(conversation: Conversation) {
        setState { AddConversationState.Loading }

        Handler(Looper.getMainLooper()).postDelayed({
            setState { AddConversationState.DataConversation(conversation = conversation) }
        }, 2000)
    }

    private fun handleRole(role: String) {
        setState { AddConversationState.GetRole(role = role) }
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
