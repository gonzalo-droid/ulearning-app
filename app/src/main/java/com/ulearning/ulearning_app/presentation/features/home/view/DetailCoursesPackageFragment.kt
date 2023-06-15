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
    BaseFragmentWithViewModel<FragmentDetailCoursePackageBinding, CoursePackageViewModel>() {

    override val binding: FragmentDetailCoursePackageBinding by dataBinding(
        FragmentDetailCoursePackageBinding::inflate
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel


    private lateinit var data: LearningPackage

    override fun onViewIsCreated(view: View) {

        arguments?.let {
            data = it.getSerializable(DETAIL_COURSES) as LearningPackage
        }

        if (::data.isInitialized) {
            with(binding) {
                titleText.text = data.title
                shortDescriptionTv.text = data.descriptionShort
                largeDescriptionTv.text = data.descriptionLarge
            }

        }
    }

    companion object {

        const val DETAIL_COURSES = "detailCourses"

        @JvmStatic
        fun newInstance(
            data: LearningPackage,
        ): DetailCoursesPackageFragment = DetailCoursesPackageFragment().apply {
            arguments = Bundle().apply {
                putSerializable(DETAIL_COURSES, data)
            }
        }
    }
}

