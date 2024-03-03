package com.ulearning.ulearning_app.presentation.features.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePackageUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePercentageUseCase
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
    private val getCoursePercentageUseCase: GetCoursePercentageUseCase,
) : BaseViewModel<CoursePackageEvent, CoursePackageState, HomeEffect>() {

    var learningPackageId = 0

    var userId = 1

    var typeRole: String = ""

    private val courseIds = arrayListOf<Int>()

    private val _coursePackage = MutableLiveData<Subscription>()
    private val coursePackage: LiveData<Subscription> = _coursePackage

    private val _items = MutableLiveData<List<LearningPackageItem>>()
    val items: LiveData<List<LearningPackageItem>> = _items

    private val _learningPackage = MutableLiveData<LearningPackage>()
    val learningPackage: LiveData<LearningPackage> = _learningPackage

    private val _percentages = MutableLiveData<List<CoursePercentage>?>()
    val percentages: LiveData<List<CoursePercentage>?> = _percentages

    fun setSharedData(data: List<LearningPackageItem>) {
        _items.postValue(data)
    }

    fun setPercentages(data: List<CoursePercentage>?) = _percentages.postValue(data)

    fun getPercentages(): List<CoursePercentage>? = percentages.value

    fun setCoursePackage(data: Subscription) {
        _coursePackage.postValue(data)
    }

    fun getCoursePackage(): Subscription? {
        return coursePackage.value
    }

    override fun createInitialState(): CoursePackageState {
        return CoursePackageState.Idle
    }

    override fun handleEvent(event: CoursePackageEvent) {
        when (event) {
            CoursePackageEvent.CoursePackageClicked -> getCoursePackageUseCase()
            CoursePackageEvent.ListCoursesPackageClicked -> getListCoursesPackageClicked()
        }
    }

    private fun getListCoursesPackageClicked() {
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

    private fun handleCourseComplete(course: Subscription) {
        // setState { CoursePackageState.CoursePackageData(course = course) }
        setCoursePackage(course)

        course.learningPackage?.items?.forEach { courseIds.add(it.courseId!!) }

        getCoursePercentage(courseIds.joinToString())
    }

    private fun getCoursePercentage(courseIds: String) {

        getCoursePercentageUseCase(
            GetCoursePercentageUseCase.Params(courseIds = courseIds)
        ) {
            it.either(::handleFailure, ::handleCoursePercentage)
        }
    }

    private fun handleCoursePercentage(percentages: List<CoursePercentage>) {
        setState {
            CoursePackageState.CoursePackageData(
                course = getCoursePackage(), percentages = percentages
            )
        }
    }

    companion object Events
}
