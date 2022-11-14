package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePercentageUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCourseUseCase
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
    private val getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCourseUseCase: GetCourseUseCase,
    private val getCoursePercentageUseCase: GetCoursePercentageUseCase,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    private val isFinished = true

    private val page = 1

    private val courseIds = arrayListOf<Int>()

    private var listCourseRecent = listOf<Subscription>()

    var userId = 1

    var typeRole: String = ""

    override fun createInitialState(): HomeState {
        return HomeState.Idle
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.CourseRecentClicked -> getCourseRecent()
            HomeEvent.CourseCompleteClicked -> getCourseComplete()
            HomeEvent.DataProfileClicked -> getProfile()
            HomeEvent.CourseTeacherClicked -> getCourseTeacher()
        }
    }

    private fun getProfile() {
        getProfileUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleProfile)
        }
    }

    private fun getCourseTeacher() {
        setState { HomeState.Loading }

        getCourseUseCase(
            GetCourseUseCase.Params(userId = userId)
        ) {
            it.either(::handleFailure, ::handleCourseTeacher)
        }
    }

    private fun getCourseComplete() {
        setState { HomeState.Loading }

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseComplete)
        }
    }

    private fun getCoursePercentage(courseIds: String) {

        getCoursePercentageUseCase(
            GetCoursePercentageUseCase.Params(courseIds = courseIds)
        ) {
            it.either(::handleFailure, ::handleCoursePercentage)
        }
    }

    private fun getCourseRecent() {
        setState { HomeState.Loading }
        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = !isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseRecent)
        }
    }


    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourseTeacher(courses: List<Course>) {
        setState { HomeState.CourseTeacher(courses = courses) }
    }

    private fun handleCourseComplete(courses: List<Subscription>) {
        setState { HomeState.CourseComplete(courses = courses) }
    }

    private fun handleCourseRecent(courses: List<Subscription>) {
        courses.forEach { courseIds.add(it.courseId) }
        getCoursePercentage(courseIds.joinToString())

        listCourseRecent = courses
    }

    private fun handleCoursePercentage(percentages: List<CoursePercentage>) {
        setState { HomeState.CourseRecent(courses = listCourseRecent, percentages = percentages) }
    }

    private fun handleProfile(data: Profile) {
        setState { HomeState.DatProfile(data = data) }
    }

    companion object Events {
    }
}