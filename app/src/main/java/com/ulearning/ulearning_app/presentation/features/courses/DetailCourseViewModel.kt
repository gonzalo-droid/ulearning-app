package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
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
) : BaseViewModel<DetailCourseEvent, DetailCourseState, DetailCourseEffect>() {


    lateinit var subscription: Subscription
    var urlWebView:String =""

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
        }
    }

    private fun goToConversation() {
        if(::subscription.isInitialized){
            setEffect { DetailCourseEffect.GoToConversation(courseId = subscription.courseId) }
        }
    }

    private fun getTopic() {
        setState { DetailCourseState.Loading }

        getTopicUseCase(
            GetTopicUseCase.Params(courseId = subscription.courseId)
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

    companion object Events {
        val goToConversation = DetailCourseEvent.GoToConversation
    }
}