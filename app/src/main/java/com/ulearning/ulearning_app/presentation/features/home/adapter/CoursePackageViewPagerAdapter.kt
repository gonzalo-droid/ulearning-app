package com.ulearning.ulearning_app.presentation.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.presentation.features.home.view.DetailCoursesPackageFragment
import com.ulearning.ulearning_app.presentation.features.home.view.ListCoursesPackageFragment

class CoursePackageViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    learningPackage: LearningPackage,
    percentages: ArrayList<CoursePercentage>?,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var data = learningPackage
    private var percentagesCourses = percentages

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ListCoursesPackageFragment.newInstance(
                list = data.items as ArrayList<LearningPackageItem>?,
                percentages = percentagesCourses,
            )
        } else {
            DetailCoursesPackageFragment.newInstance(data)
        }
    }
}
