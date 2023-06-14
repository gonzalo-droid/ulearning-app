package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentDetailCoursePackageBinding
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageItemAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackageEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.DetailCoursesPackageReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackageViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.DetailCoursesPackageViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCoursesPackageFragment :
    BaseFragmentWithViewModel<FragmentDetailCoursePackageBinding, CoursePackageViewModel>(),
    DetailCoursesPackageViewState {

    override val binding: FragmentDetailCoursePackageBinding by dataBinding(FragmentDetailCoursePackageBinding::inflate)

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel

    override fun onViewIsCreated(view: View) {

        DetailCoursesPackageReducer.instance(viewState = this)

        // observeUiStates()

        val data : LearningPackage? = (requireActivity() as CoursePackageActivity).returnLearningPackage()
        if(data != null){
            with(binding){
                titleText.text = data.title
                shortDescriptionTv.text = data.descriptionShort
                largeDescriptionTv.text = data.descriptionLarge
            }

        }
    }

    private fun observeUiStates() {
        viewModel.setEvent(CoursePackageEvent.ListCoursesPackageClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    DetailCoursesPackageReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    DetailCoursesPackageReducer.selectEffect(effect)
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
