package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesPackageSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.event.CourseRouteEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CourseRouteState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseRouteViewModel
@Inject constructor(
    private val getCoursesPackageSubscriptionUseCase: GetCoursesPackageSubscriptionUseCase,
) : BaseViewModel<CourseRouteEvent, CourseRouteState, HomeEffect>() {

    private val isFinished = true

    private val page = 1

    var userId = 1

    var typeRole: String = ""

    override fun createInitialState(): CourseRouteState {
        return CourseRouteState.Idle
    }

    override fun handleEvent(event: CourseRouteEvent) {
        when (event) {
            CourseRouteEvent.CourseRouteClicked -> getRoutes()
        }
    }

    private fun getRoutes() {
        setState { CourseRouteState.Loading }

        getCoursesPackageSubscriptionUseCase(
            GetCoursesPackageSubscriptionUseCase.Params(page = page, type = "path")
        ) {
            it.either(::handleFailure, ::handleCourseRoute)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourseRoute(courses: List<Subscription>) {
        setState { CourseRouteState.CourseRoute(courses = courses) }
    }

    companion object Events
}
