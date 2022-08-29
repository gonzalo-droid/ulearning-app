package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    private val isFinished = true
    private val page = 1

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
        setState { HomeState.Loading }

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = !isFinished)
        ) {
            it.either(::handleFailure, ::handleCourse)
        }
    }

    private fun listRecentlyCourses() {
        setState { HomeState.Loading }

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = !isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseRecently)
        }
    }

    private fun goCourses() {

    }

    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourse(courses: List<Subscription>) {
        setState { HomeState.CourseList(courses = courses) }
    }

    private fun handleCourseRecently(courses: List<Subscription>) {
        setState { HomeState.CourseRecentlyList(courses = courses) }
    }

    companion object Events {
        val goToCoursesClicked = HomeEvent.GoToCoursesClicked
    }
}