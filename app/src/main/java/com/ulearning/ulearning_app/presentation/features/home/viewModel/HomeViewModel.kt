package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    override fun createInitialState(): HomeState {
        return HomeState.Idle
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.GoToCoursesClicked -> goCourses()
            HomeEvent.RecentlyCoursesHomeClicked -> listRecentlyCourses()
            HomeEvent.CoursesHomeClicked -> listCoursesHome()

        }
    }

    private fun listCoursesHome() {

    }

    private fun listRecentlyCourses() {

    }

    private fun goCourses() {

    }



    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleSuccess(success: Boolean) {
        setState { HomeState.LoginSuccess(success = success) }
    }

    companion object Events {
        val goToCoursesClicked = HomeEvent.GoToCoursesClicked
    }
}