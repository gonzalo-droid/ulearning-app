package com.ulearning.ulearning_app.presentation.features.courses

import android.app.Activity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.domain.useCase.topic.GetTopicUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.topic.TopicState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCourseViewModel
@Inject constructor(
    private val getTopicUseCase: GetTopicUseCase
) : BaseViewModel<DetailCourseEvent, DetailCourseState, DetailCourseEffect>() {

    var courseId = 0

    override fun createInitialState(): DetailCourseState {
        return DetailCourseState.Idle
    }

    override fun handleEvent(event: DetailCourseEvent) {
        when (event) {
            DetailCourseEvent.DataDetailCourseClicked -> getDetailCourse()
            DetailCourseEvent.GoToTopic -> getTopic()
            DetailCourseEvent.SendComment -> {}
        }
    }

    private fun getTopic() {
        setState { DetailCourseState.Loading }

        getTopicUseCase(
            GetTopicUseCase.Params(courseId = courseId)
        ) {
            it.either(::handleFailure, ::handleTopics)
        }
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

    fun forActivityResult(resultCode: Int, method: () -> Unit) {

        if (resultCode == Activity.RESULT_OK) {

            method()
        }
    }


    companion object Events {
        val goToTopic = DetailCourseEvent.GoToTopic
        val sendComment = DetailCourseEvent.SendComment
    }
}