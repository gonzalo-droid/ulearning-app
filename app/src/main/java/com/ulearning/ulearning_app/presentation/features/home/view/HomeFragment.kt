package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.view.View
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
import com.ulearning.ulearning_app.presentation.features.home.event.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.HomeReducer
import com.ulearning.ulearning_app.presentation.features.home.viewState.HomeViewState
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseAdapter
import com.ulearning.ulearning_app.presentation.features.home.viewModel.HomeViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentWithViewModel<FragmentHomeBinding, HomeViewModel>(),
    HomeViewState {

    override val binding: FragmentHomeBinding by dataBinding(FragmentHomeBinding::inflate)

    override val viewModel: HomeViewModel by viewModels()

    override val dataBindingViewModel = BR.homeViewModel

    private lateinit var courseTeacherRecycler: RecyclerView


    override fun onViewIsCreated(view: View) {

        HomeReducer.instance(viewState = this)

        observeUiStates()

        courseTeacherRecycler = binding.courseTeacherRecycler

        courseTeacherRecycler.layoutManager = LinearLayoutManager(requireActivity())


        binding.cardProgress.setOnClickListener {
            navigateToCourseProgressFragment()
        }

        binding.cardCompleted.setOnClickListener {
            navigateToCourseCompletedFragment()
        }
    }

    private fun navigateToCourseProgressFragment() {
        findNavController().navigate(
            R.id.action_navigation_home_to_courseProgressActivity, Bundle()
        )
    }

    private fun navigateToCourseCompletedFragment() {
        findNavController().navigate(
            R.id.action_navigation_home_to_courseCompletedActivity, Bundle()
        )
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

        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

    private fun onItemSelected(model: Subscription) {

        findNavController().navigate(R.id.action_navigation_home_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_PUT, model.course)
                putSerializable(Config.SUBSCRIPTION_PUT, model)
                putSerializable(Config.ROLE, viewModel.typeRole)
            })
    }

    override fun getCourseTeacher(courses: List<Course>) {
        closeLoadingDialog()
        val subs = Subscription(
            courseId = 0,
        )

        if (courses.isNotEmpty()) {
            binding.courseTeacherRecycler.visibility = View.VISIBLE
            binding.noDataInclude.noDataLayout.visibility = View.INVISIBLE
        } else {
            binding.noDataInclude.noDataLayout.visibility = View.VISIBLE
            binding.courseTeacherRecycler.visibility = View.INVISIBLE
        }

        courseTeacherRecycler.adapter = CourseAdapter(courses = courses) { model ->
            run {
                subs.course = model
                onItemSelected(subs)
            }
        }
    }


    override fun getProfile(data: Profile) {
        closeLoadingDialog()
        with(binding) {
            tvUserName.text = data.name?.trim()
            if (data.role.equals(Config.ROLE_TEACHER)) {
                viewModel.userId = data.id!!
                studentConstraintLayout.visibility = View.INVISIBLE
                teacherConstraintLayout.visibility = View.VISIBLE

                viewModel.typeRole = Config.ROLE_TEACHER

                viewModel.setEvent(HomeEvent.CourseTeacherClicked)

            } else { // student
                studentConstraintLayout.visibility = View.VISIBLE
                teacherConstraintLayout.visibility = View.INVISIBLE
                viewModel.typeRole = Config.ROLE_STUDENT
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
    }
}