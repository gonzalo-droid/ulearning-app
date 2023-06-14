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
import com.ulearning.ulearning_app.databinding.FragmentListCoursesPackageBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageItemAdapter
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackageEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CoursePackageReducer
import com.ulearning.ulearning_app.presentation.features.home.reducer.ListCoursesPackageReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackageViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.ListCoursesPackageViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCoursesPackageFragment :
    BaseFragmentWithViewModel<FragmentListCoursesPackageBinding, CoursePackageViewModel>(),
    ListCoursesPackageViewState {

    override val binding: FragmentListCoursesPackageBinding by dataBinding(
        FragmentListCoursesPackageBinding::inflate
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel

    private lateinit var courseRecycler: RecyclerView

    override fun onViewIsCreated(view: View) {

        ListCoursesPackageReducer.instance(viewState = this)

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireActivity())

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireContext())

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(CoursePackageEvent.ListCoursesPackageClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    CoursePackageReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    CoursePackageReducer.selectEffect(effect)
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

    override fun getListCoursesPackage(items: List<LearningPackageItem>?) {
        closeLoadingDialog()

        courseRecycler.adapter = CoursePackageItemAdapter(
            items = items!!
        ) { model ->
            onItemSelected(model)
        }

        /*
        courseRecycler.adapter =
            CourseSubscriptionAdapter(courses = courses, percentages = percentages) { model ->
                onItemSelected(model)
            }
        */
    }

    private fun onItemSelected(model: LearningPackageItem) {

        findNavController().navigate(R.id.action_navigation_home_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_PUT, model.course)
                // putSerializable(Config.SUBSCRIPTION_PUT, model)
                putSerializable(Config.ROLE, viewModel.typeRole)
            })
    }
}
