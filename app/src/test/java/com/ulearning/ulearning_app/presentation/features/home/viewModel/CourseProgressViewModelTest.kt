package com.ulearning.ulearning_app.presentation.features.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursePercentageUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCourseUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.features.home.event.CourseProgressEvent
import com.ulearning.ulearning_app.utils.Config
import com.ulearning.ulearning_app.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CourseProgressViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getCoursesSubscriptionUseCase: GetCoursesSubscriptionUseCase
    private lateinit var getCourseUseCase: GetCourseUseCase
    private lateinit var getCoursePercentageUseCase: GetCoursePercentageUseCase

    private lateinit var resultStatusLiveData: MutableLiveData<Result<Any>>

    private lateinit var viewModel: CourseProgressViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        getCoursesSubscriptionUseCase = mockk()
        getCourseUseCase = mockk()
        getCoursePercentageUseCase = mockk()

        viewModel = CourseProgressViewModel(
            getCoursesSubscriptionUseCase, getCourseUseCase, getCoursePercentageUseCase
        )
    }


    @Test
    fun `test get progress course success`() = runTest {
        val gson = Gson()
        val jsonContent = Config.readJsonFile("list_progress_courses.json")


        val list = object : TypeToken<List<SubscriptionResponse>>() {}.type
        val jsonObject = JsonParser.parseString(jsonContent).asJsonObject
        val jsonArray = jsonObject.getAsJsonArray("data")
        val benefitResponse: List<Subscription> = gson.fromJson(jsonArray, list)


        // Create a test dispatcher and test scope
        val testDispatcher = StandardTestDispatcher()
        val testScope = TestScope(testDispatcher)

        testScope.launch {
            // Mock the behavior of getBenefitByID in the use case
            /*
            coEvery {
                getCoursesSubscriptionUseCase(params = GetCoursesSubscriptionUseCase.Params(
                    page = 1, isFinished = false
                ), onResult = {})
            } returns Either.Right(
                benefitResponse
            )

            // Execute the method being tested
            viewModel.setEvent(CourseProgressEvent.CourseRecentClicked)

            // Verificar que los datos se hayan actualizado correctamente en el ViewModel
            assertEquals(benefitResponse.benefitsList.categories, viewModel.listBenefits.value)
            assertEquals(false, viewModel.handleError.value)*/

            // Verify that the relevant methods were called
            coVerify(exactly = 1) {
                getCoursesSubscriptionUseCase(params = GetCoursesSubscriptionUseCase.Params(
                    page = 1, isFinished = false
                ), onResult = {})
            }
        }
    }
}
