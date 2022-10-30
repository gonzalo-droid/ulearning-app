package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentHomeBinding
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeReducer
import com.ulearning.ulearning_app.presentation.features.home.HomeViewState
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseAdapter
import com.ulearning.ulearning_app.presentation.features.home.adapter.SubscriptionCourseAdapter
import com.ulearning.ulearning_app.presentation.features.home.adapter.SubscriptionCourseRecentlyAdapter
import com.ulearning.ulearning_app.presentation.features.home.viewModel.HomeViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragmentWithViewModel<FragmentHomeBinding, HomeViewModel>(),
    HomeViewState {

    override val binding: FragmentHomeBinding by dataBinding(FragmentHomeBinding::inflate)

    override val viewModel: HomeViewModel by viewModels()

    override val dataBindingViewModel = BR.loginViewModel

    private lateinit var courseRecentlyRecycler: RecyclerView

    private lateinit var courseRecycler: RecyclerView

    private lateinit var courseAdapter: CourseAdapter

    private lateinit var subscriptionCourseAdapter: SubscriptionCourseAdapter

    private lateinit var subscriptionCourseRecentlyAdapter: SubscriptionCourseRecentlyAdapter



    override fun onViewIsCreated(view: View) {

        HomeReducer.instance(viewState = this)

        observeUiStates()

        courseRecycler = binding.courseRecycler

        courseRecentlyRecycler = binding.courseRecentlyRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireActivity())

        courseRecentlyRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)


    }

    private fun observeUiStates() {
        viewModel.setEvent(HomeEvent.DataProfileClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    HomeReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    HomeReducer.selectEffect(effect)
                }
            })
        }

    }

    override fun messageFailure(failure: Failure) {

        closeShimmer()

        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        with(binding){
            successLayout.isVisible = false
            loadingLayout.isVisible = true
        }
    }

    override fun courseSubscriptionRecentlyList(courses: List<Subscription>) {

        closeShimmer()

        subscriptionCourseRecentlyAdapter = SubscriptionCourseRecentlyAdapter(courses = courses){
                model -> onItemSelected(model)
        }

        courseRecentlyRecycler.adapter = subscriptionCourseRecentlyAdapter

    }

    override fun  courseSubscriptionList(courses: List<Subscription>) {

        closeShimmer()

        subscriptionCourseAdapter = SubscriptionCourseAdapter(courses = courses) {
                model -> onItemSelected(model)
        }

        courseRecycler.adapter = subscriptionCourseAdapter
    }

    override fun  courseList(courses: List<Course>) {

        closeShimmer()

        val subs = Subscription(
            amount = null,
            course = null,
            courseId = 0,
            group = null,
            groupId = null,
            hasCertificate = null,
            hasDegree = null,
            hasRecord = null,
            id = null,
            isFinished = null,
            purchasedCertificate = null,
            purchasedRecord = null,
            status = null,
            timeSession = null,
            type = null,
            user = null,
            userId = null
        )

        courseAdapter = CourseAdapter(courses = courses) {
                model ->
            run {
                subs.course = model
                onItemSelected(subs)
            }
        }

        courseRecycler.adapter = courseAdapter
    }

    private fun closeShimmer() {
        with(binding){
            successLayout.isVisible = true
            loadingLayout.isVisible = false
        }
    }

    private fun onItemSelected(model: Subscription){

        findNavController().navigate(
            R.id.action_navigation_home_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_PUT, model.course)
                putSerializable(Config.SUBSCRIPTION_PUT, model)
            }
        )
    }

    override fun getProfile(data: Profile) {
        closeLoadingDialog()
        with(binding) {
            tvUserName.text = data.name
            if(data.role.equals(Config.ROLE_TEACHER)){
                continueLearningText.visibility = View.GONE
                courseRecentlyRecycler.visibility = View.GONE

                viewModel.userId = data.id!!
                viewModel.setEvent(HomeEvent.CoursesHomeTeacherClicked)
            } else {
                viewModel.setEvent(HomeEvent.RecentlyCoursesHomeClicked)
                viewModel.setEvent(HomeEvent.CoursesHomeClicked)
            }
        }
    }
}