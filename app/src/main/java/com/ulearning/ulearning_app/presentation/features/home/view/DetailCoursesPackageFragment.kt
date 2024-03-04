package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.fragment.app.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.databinding.FragmentDetailCoursePackageBinding
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCoursesPackageFragment :
    BaseFragmentWithViewModel<FragmentDetailCoursePackageBinding, CoursePackageViewModel>() {
    override val binding: FragmentDetailCoursePackageBinding by dataBinding(
        FragmentDetailCoursePackageBinding::inflate,
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
                shortDescriptionTv.text = data.descriptionShort?.let { parseHtmlToString(it) }
                largeDescriptionTv.text = data.descriptionLarge?.let { parseHtmlToString(it) }
            }
        }
    }

    private fun parseHtmlToString(html: String): String {
        val spanned: Spanned = Html.fromHtml(html)
        return spanned.toString()
    }

    companion object {
        const val DETAIL_COURSES = "detailCourses"

        @JvmStatic
        fun newInstance(data: LearningPackage): DetailCoursesPackageFragment =
            DetailCoursesPackageFragment().apply {
                arguments =
                    Bundle().apply {
                        putSerializable(DETAIL_COURSES, data)
                    }
            }
    }
}
