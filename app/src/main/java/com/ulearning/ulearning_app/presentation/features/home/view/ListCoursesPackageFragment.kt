package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    BaseFragmentWithViewModel<FragmentListCoursesPackageBinding, CoursePackageViewModel>(){

    override val binding: FragmentListCoursesPackageBinding by dataBinding(
        FragmentListCoursesPackageBinding::inflate
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel

    private lateinit var courseRecycler: RecyclerView

    private var items: ArrayList<LearningPackageItem> = arrayListOf()

    override fun onViewIsCreated(view: View) {

        arguments?.let {
            items = it.getSerializable(LIST_COURSES) as ArrayList<LearningPackageItem>
        }


        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(requireContext())

        observeUiStates()

    }

    private fun observeUiStates() {
        courseRecycler.adapter = CoursePackageItemAdapter(
            items = items!!
        ) { model ->
            onItemSelected(model)
        }
    }

    private fun onItemSelected(model: LearningPackageItem) {


        val subscription = Subscription(
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
            userId = null,
            learningPackage = null
        )
        findNavController().navigate(
            R.id.action_listCoursesPackageFragment_to_detailCourseActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_PUT, model.course)
                putSerializable(Config.SUBSCRIPTION_PUT, subscription)
            })
    }


    companion object {

        const val LIST_COURSES = "listCourses"

        @JvmStatic
        fun newInstance(
            list: ArrayList<LearningPackageItem>,
        ): ListCoursesPackageFragment = ListCoursesPackageFragment().apply {
            arguments = Bundle().apply {
                putSerializable(LIST_COURSES, list)
            }
        }
    }
}
