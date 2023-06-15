package com.ulearning.ulearning_app.presentation.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.presentation.features.home.view.DetailCoursesPackageFragment
import com.ulearning.ulearning_app.presentation.features.home.view.ListCoursesPackageFragment

class CoursePackageViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    learningPackage: LearningPackage,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    var items = learningPackage
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ListCoursesPackageFragment.newInstance(
                (items.items
                    ?: arrayListOf<LearningPackageItem>()) as ArrayList<LearningPackageItem>
            )
        } else {
            DetailCoursesPackageFragment.newInstance(items)
        }
    }
}
