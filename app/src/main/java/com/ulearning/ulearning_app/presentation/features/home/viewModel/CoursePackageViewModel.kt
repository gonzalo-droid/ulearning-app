package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackState
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackageEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CoursePackageViewModel
@Inject constructor(
    private val getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase,
) : BaseViewModel<CoursePackageEvent, CoursePackageState, HomeEffect>() {

    private val isFinished = true

    private val page = 1

    var userId = 1

    var typeRole: String = ""

    override fun createInitialState(): CoursePackageState {
        return CoursePackageState.Idle
    }


    override fun handleEvent(event: CoursePackageEvent) {
        when (event) {
            CoursePackageEvent.CoursePackageClicked -> getCourseComplete()
        }
    }

    private fun getCourseComplete() {
        setState { CoursePackageState.Loading }

        getCoursesSubscriptionUseCase(
            GetCoursesSubscriptionUseCase.Params(page = page, isFinished = isFinished)
        ) {
            it.either(::handleFailure, ::handleCourseComplete)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }


    private fun handleCourseComplete(courses: List<Subscription>) {
        setState { CoursePackageState.CoursePackage(courses = courses) }
    }

    companion object Events
}