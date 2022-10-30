package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCourseUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeState
import com.ulearning.ulearning_app.presentation.features.profile.ProfileEvent
import com.ulearning.ulearning_app.presentation.features.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCourseUseCase: GetCourseUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    private val isFinished = true
    private val page = 1

    var userId = 1

    override fun createInitialState(): HomeState {
        return HomeState.Idle
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.RecentlyCoursesHomeClicked -> listRecentlyCourses()
            HomeEvent.CoursesHomeClicked -> listCoursesHome()
            HomeEvent.DataProfileClicked -> getProfile()
            HomeEvent.CoursesHomeTeacherClicked -> listCoursesTeacherHome()
        }
    }

    private fun getProfile() {
        getProfileUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleProfile)
        }
    }

    private fun listCoursesTeacherHome() {
        setState { HomeState.Loading }

        getCourseUseCase(
            GetCourseUseCase.Params(userId = userId)
        ) {
            it.either(::handleFailure, ::handleCourse)
        }
    }

    private fun listCoursesHome() {
        setState { HomeState.Loading }

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = !isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseSubscription)
        }
    }

    private fun listRecentlyCourses() {

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = !isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseSubscriptionRecently)
        }
    }


    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourse(courses: List<Course>) {
        setState { HomeState.CourseList(courses = courses) }
    }

    private fun handleCourseSubscription(courses: List<Subscription>) {
        setState { HomeState.CourseSubscriptionList(courses = courses) }
    }

    private fun handleCourseSubscriptionRecently(courses: List<Subscription>) {
        setState { HomeState.CourseSubscriptionRecentlyList(courses = courses) }
    }

    private fun handleProfile(data: Profile) {
        setState { HomeState.DatProfile(data = data) }
    }

    companion object Events {
    }
}