package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesPackageSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoursePackViewModel
    @Inject
    constructor(
        private val getCoursesPackageSubscriptionUseCase: GetCoursesPackageSubscriptionUseCase,
    ) : BaseViewModel<CoursePackEvent, CoursePackState, HomeEffect>() {
        private val isFinished = true

        private val page = 1

        var userId = 1

        var typeRole: String = ""

        override fun createInitialState(): CoursePackState {
            return CoursePackState.Idle
        }

        override fun handleEvent(event: CoursePackEvent) {
            when (event) {
                CoursePackEvent.CoursePackClicked -> getPacks()
            }
        }

        private fun getPacks() {
            setState { CoursePackState.Loading }

            getCoursesPackageSubscriptionUseCase(
                GetCoursesPackageSubscriptionUseCase.Params(page = page, type = "package"),
            ) {
                it.either(::handleFailure, ::handleCourseComplete)
            }
        }

        private fun handleFailure(failure: Failure) {
            setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
        }

        private fun handleCourseComplete(courses: List<Subscription>) {
            setState { CoursePackState.CoursePack(courses = courses) }
        }

        companion object Events
    }
