package com.ulearning.ulearning_app.presentation.features.home.viewModel

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePackageUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackageEvent
import com.ulearning.ulearning_app.presentation.features.home.state.CoursePackageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoursePackageViewModel
@Inject constructor(
    private val getCoursePackageUseCase: GetCoursePackageUseCase,
) : BaseViewModel<CoursePackageEvent, CoursePackageState, HomeEffect>() {

    var learningPackageId = 0

    var items: List<LearningPackageItem>? = arrayListOf()

    var userId = 1

    var typeRole: String = ""

    override fun createInitialState(): CoursePackageState {
        return CoursePackageState.Idle
    }

    override fun handleEvent(event: CoursePackageEvent) {
        when (event) {
            CoursePackageEvent.CoursePackageClicked -> getCoursePackageUseCase()
            CoursePackageEvent.ListCoursesPackageClicked -> getListCoursesPackageClicked()
        }
    }

    private fun getListCoursesPackageClicked(){
        setState { CoursePackageState.Loading }

        handleListCourseComplete(items)
    }

    private fun getCoursePackageUseCase() {
        setState { CoursePackageState.Loading }

        getCoursePackageUseCase(
            GetCoursePackageUseCase.Params(learningPackageId = learningPackageId)
        ) {
            it.either(::handleFailure, ::handleCourseComplete)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleCourseComplete(course: CoursePackage) {
        setState { CoursePackageState.CoursePackageData(course = course) }
    }

    private fun handleListCourseComplete(items: List<LearningPackageItem>?) {
        setState { CoursePackageState.ListCoursesPackage(items = items) }
    }


    companion object Events
}
