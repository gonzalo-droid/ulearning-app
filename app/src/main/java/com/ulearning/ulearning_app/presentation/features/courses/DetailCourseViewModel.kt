package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.*
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetRoleUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCheckAvailableFilesUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetDownloadFileUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetMyFilesUseCase
import com.ulearning.ulearning_app.domain.useCase.topic.GetTopicUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.profile.ProfileEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCourseViewModel
@Inject constructor(
    private val getTopicUseCase: GetTopicUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getRoleUseCase: GetRoleUseCase,
    private val getMyFilesUseCase: GetMyFilesUseCase,
    private val checkAvailableFilesUseCase: GetCheckAvailableFilesUseCase,
    private val downloadFileUseCase: GetDownloadFileUseCase
) : BaseViewModel<DetailCourseEvent, DetailCourseState, DetailCourseEffect>() {


    lateinit var course: Course
    lateinit var subscription: Subscription
    var urlWebView: String = ""
    var typeRole: String = ""

    override fun createInitialState(): DetailCourseState {
        return DetailCourseState.Idle
    }

    override fun handleEvent(event: DetailCourseEvent) {
        when (event) {
            DetailCourseEvent.DataDetailCourseClicked -> getDetailCourse()
            DetailCourseEvent.GetTopic -> getTopic()
            DetailCourseEvent.SendComment -> {}
            DetailCourseEvent.GoToConversation -> goToConversation()
            DetailCourseEvent.GetToken -> getToken()
            DetailCourseEvent.GetRole -> getRole()
            DetailCourseEvent.GetCheckAvailableFiles -> getCheckAvailableFiles()
            DetailCourseEvent.GetDownloadFile -> TODO()
            DetailCourseEvent.GetMyFiles -> getMyFiles()
        }
    }

    private fun getCheckAvailableFiles() {
        if (subscription.id != 0) {
            checkAvailableFilesUseCase(
                GetCheckAvailableFilesUseCase.Params(subscriptionId = subscription.id!!)
            ) {
                it.either(::handleFailure, ::handleCheckFiles)
            }
        }
    }

    private fun getMyFiles() {
        getMyFilesUseCase(
            GetMyFilesUseCase.Params(subscriptionId = subscription.id!!)
        ) {
            it.either(::handleFailure, ::handleMyFiles)
        }
    }

    private fun getRole() = getRoleUseCase(BaseUseCase.None()) {
        it.either(::handleFailure, ::handleRole)
    }

    private fun goToConversation() {
        if (::course.isInitialized) {
            setEffect { DetailCourseEffect.GoToConversation(courseId = course.id) }
        }
    }

    private fun getTopic() {
        setState { DetailCourseState.Loading }

        getTopicUseCase(
            GetTopicUseCase.Params(courseId = course.id)
        ) {
            it.either(::handleFailure, ::handleTopics)
        }

    }

    private fun getToken() {
        getTokenUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleToken)
        }
    }

    private fun handleRole(role: String) {
        typeRole = role
    }

    private fun handleToken(url: String) {
        urlWebView = url
    }

    private fun getDetailCourse() {
        setState { DetailCourseState.Loading }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { DetailCourseEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleTopics(topics: List<Topic>) {
        setState { DetailCourseState.ListTopic(topics = topics) }
    }

    private fun handleCheckFiles(checkAvailableFiles: CheckAvailableFiles) {
        setState { DetailCourseState.CheckFiles(checkAvailableFiles = checkAvailableFiles) }
    }

    private fun handleMyFiles(files: List<FileItem>) {
        setState { DetailCourseState.MyFiles(files = files) }
    }

    companion object Events {
        val goToConversation = DetailCourseEvent.GoToConversation
    }
}