package com.ulearning.ulearning_app.presentation.features.home.view

import android.content.Intent
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
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeReducer
import com.ulearning.ulearning_app.presentation.features.home.HomeViewState
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseAdapter
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseRecentlyAdapter
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

    private lateinit var courseRecentlyAdapter: CourseRecentlyAdapter

    private var courseList: List<Subscription> = arrayListOf()

    private var courseRecentlyList: List<Subscription> = arrayListOf()


    override fun onViewIsCreated(view: View) {

        HomeReducer.instance(viewState = this)

        observeUiStates()

        courseRecycler = binding.courseRecycler

        courseRecentlyRecycler = binding.courseRecentlyRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireActivity())

        courseRecentlyRecycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)


    }

    private fun observeUiStates() {
        viewModel.setEvent(HomeEvent.RecentlyCoursesHomeClicked)
        viewModel.setEvent(HomeEvent.CoursesHomeClicked)

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

    override fun courseRecentlyList(courses: List<Subscription>) {

        closeShimmer()
        courseRecentlyList = courses

        val userName = courses.first().user?.name
        binding.tvUserName.text = userName

        courseRecentlyAdapter = CourseRecentlyAdapter(courses = courseRecentlyList){
                model -> onItemSelected(model)
        }

        courseRecentlyRecycler.adapter = courseRecentlyAdapter

    }

    override fun  courseList(courses: List<Subscription>) {

        closeShimmer()

        courseList = courses

        courseAdapter = CourseAdapter(courses = courseList) {
                model -> onItemSelected(model)
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

/*
        startActivity(Intent(requireActivity(), DetailCourseActivity::class.java).apply {

            putExtra(Config.SUBSCRIPTION_PUT, model)

        })
*/

        findNavController().navigate(
            R.id.action_navigation_home_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.SUBSCRIPTION_PUT, model)
            }
        )



    }
}