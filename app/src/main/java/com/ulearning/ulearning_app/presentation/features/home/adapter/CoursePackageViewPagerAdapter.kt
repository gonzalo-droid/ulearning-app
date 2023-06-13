package com.ulearning.ulearning_app.presentation.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ulearning.ulearning_app.presentation.features.home.view.DetailPackageFragment
import com.ulearning.ulearning_app.presentation.features.home.view.ListCoursesPackageFragment

class CoursePackageViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ListCoursesPackageFragment()
        } else {
            DetailPackageFragment()
        }
    }
}