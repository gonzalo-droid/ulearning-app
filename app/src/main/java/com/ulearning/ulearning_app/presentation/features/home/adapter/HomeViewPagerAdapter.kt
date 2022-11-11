package com.ulearning.ulearning_app.presentation.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ulearning.ulearning_app.presentation.features.home.view.CourseCompletedFragment
import com.ulearning.ulearning_app.presentation.features.home.view.CourseFragment


class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            CourseFragment()
        } else {
            CourseCompletedFragment()
        }
    }
}
