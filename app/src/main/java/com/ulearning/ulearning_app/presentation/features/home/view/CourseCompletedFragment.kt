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
import com.ulearning.ulearning_app.databinding.FragmentCourseCompleteBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.*
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.viewModel.HomeViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseCompletedFragment :
    BaseFragmentWithViewModel<FragmentCourseCompleteBinding, HomeViewModel>(),
    CourseCompleteViewState {

    override val binding: FragmentCourseCompleteBinding by dataBinding(FragmentCourseCompleteBinding::inflate)

    override val viewModel: HomeViewModel by viewModels()

    override val dataBindingViewModel = BR.homeViewModel

    private lateinit var courseRecycler: RecyclerView


    override fun onViewIsCreated(view: View) {

        CourseCompleteReducer.instance(viewState = this)

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireActivity())

        observeUiStates()

    }

    private fun observeUiStates() {
        viewModel.setEvent(HomeEvent.CourseCompleteClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    CourseCompleteReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    CourseCompleteReducer.selectEffect(effect)
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

    override fun getCourseComplete(courses: List<Subscription>) {
        closeLoadingDialog()
        courseRecycler.adapter = CourseSubscriptionAdapter(courses = courses) { model ->
            onItemSelected(model)
        }
    }

    private fun onItemSelected(model: Subscription) {

        findNavController().navigate(
            R.id.action_navigation_home_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_PUT, model.course)
                putSerializable(Config.SUBSCRIPTION_PUT, model)
                putSerializable(Config.ROLE, viewModel.typeRole)
            }
        )
    }
}