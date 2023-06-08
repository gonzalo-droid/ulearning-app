package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePercentageUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCourseUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.event.CourseProgressEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CourseProgressState
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CourseProgressViewModel
@Inject constructor(
    private val getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase,
    private val getCourseUseCase: GetCourseUseCase,
    private val getCoursePercentageUseCase: GetCoursePercentageUseCase,
) : BaseViewModel<CourseProgressEvent, CourseProgressState, HomeEffect>() {

    private val isFinished = true

    private val page = 1

    private val courseIds = arrayListOf<Int>()

    private var listCourseRecent = listOf<Subscription>()

    var userId = 1

    var typeRole: String = ""

    override fun createInitialState(): CourseProgressState {
        return CourseProgressState.Idle
    }

    override fun handleEvent(event: CourseProgressEvent) {
        when (event) {
            CourseProgressEvent.CourseRecentClicked -> getCourseRecent()
            CourseProgressEvent.CourseTeacherClicked -> getCourseTeacher()
        }
    }

    private fun getCourseTeacher() {
        setState { CourseProgressState.Loading }

        getCourseUseCase(
            GetCourseUseCase.Params(userId = userId)
        ) {
            it.either(::handleFailure, ::handleCourseTeacher)
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
        setState { CourseProgressState.Loading }
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
        setState { CourseProgressState.CourseTeacher(courses = courses) }
    }

    private fun handleCourseRecent(courses: List<Subscription>) {
        courses.forEach { courseIds.add(it.courseId) }
        getCoursePercentage(courseIds.joinToString())

        listCourseRecent = courses
    }

    private fun handleCoursePercentage(percentages: List<CoursePercentage>) {
        setState { CourseProgressState.CourseRecent(courses = listCourseRecent, percentages = percentages) }
    }


    companion object Events {
    }
}