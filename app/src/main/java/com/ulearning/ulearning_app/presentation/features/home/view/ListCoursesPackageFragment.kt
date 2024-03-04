package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.databinding.FragmentListCoursesPackageBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageItemAdapter
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCoursesPackageFragment :
    BaseFragmentWithViewModel<FragmentListCoursesPackageBinding, CoursePackageViewModel>() {
    override val binding: FragmentListCoursesPackageBinding by dataBinding(
        FragmentListCoursesPackageBinding::inflate,
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel

    private lateinit var courseRecycler: RecyclerView

    private var items: ArrayList<LearningPackageItem> = arrayListOf()
    private var percentages: ArrayList<CoursePercentage> = arrayListOf()

    override fun onViewIsCreated(view: View) {
        arguments?.let {
            items = it.getSerializable(LIST_COURSES) as ArrayList<LearningPackageItem>
            percentages = it.getSerializable(LIST_PERCENTAGES) as ArrayList<CoursePercentage>
        }

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireContext())

        observeUiStates()
    }

    private fun observeUiStates() {
        courseRecycler.adapter =
            CoursePackageItemAdapter(
                items = items, percentages = percentages,
            ) { model, percentage ->
                onItemSelected(model, percentage)
            }
    }

    private fun onItemSelected(
        model: LearningPackageItem,
        percentage: Int,
    ) {
        (requireActivity() as CoursePackageActivity).goToDetailCourse(model, percentage)
    }

    companion object {
        const val LIST_COURSES = "listCourses"
        const val LIST_PERCENTAGES = "percentages"

        @JvmStatic
        fun newInstance(
            list: ArrayList<LearningPackageItem>?,
            percentages: ArrayList<CoursePercentage>? = arrayListOf(),
        ): ListCoursesPackageFragment =
            ListCoursesPackageFragment().apply {
                arguments =
                    Bundle().apply {
                        putSerializable(LIST_COURSES, list)
                        putSerializable(LIST_PERCENTAGES, percentages)
                    }
            }
    }
}
