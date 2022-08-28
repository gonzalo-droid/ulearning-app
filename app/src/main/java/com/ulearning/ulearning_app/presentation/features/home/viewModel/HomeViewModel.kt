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
        setState { HomeState.Loading }
        handleCourse(arrayListOf())
    }

    private fun listRecentlyCourses() {
        handleCourseRecently(arrayListOf())
    }

    private fun goCourses() {

    }
    private fun doLogin() {

    }

    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourse(courses: List<Course>) {
        setState { HomeState.CourseList(courses = courses) }
    }

    private fun handleCourseRecently(courses: List<Course>) {
        setState { HomeState.CourseRecentlyList(courses = courses) }
    }

    companion object Events {
        val goToCoursesClicked = HomeEvent.GoToCoursesClicked
    }
}