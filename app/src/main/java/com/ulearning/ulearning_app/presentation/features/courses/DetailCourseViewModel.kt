package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCourseViewModel
@Inject constructor(

) : BaseViewModel<DetailCourseEvent, DetailCourseState, DetailCourseEffect>() {

    override fun createInitialState(): DetailCourseState {
        return DetailCourseState.Idle
    }

    override fun handleEvent(event: DetailCourseEvent) {
        when (event) {
            DetailCourseEvent.DataDetailCourseClicked -> getDetailCourse()
        }
    }


    private fun getDetailCourse() {
        setState { DetailCourseState.Loading }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { DetailCourseEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleDetailCourse(data: Subscription) {
        setState { DetailCourseState.DataDetailCourse(data = data) }
    }

    private fun handleLogout(value : Boolean) {
        setEffect { DetailCourseEffect.Logout }
    }


    companion object Events {
        val dataDetailCourseClicked = DetailCourseEvent.DataDetailCourseClicked

    }
}