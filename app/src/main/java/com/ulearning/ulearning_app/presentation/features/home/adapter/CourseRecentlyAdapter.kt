package com.ulearning.ulearning_app.presentation.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ListCoursesItemBinding
import com.ulearning.ulearning_app.databinding.ListCoursesRecentlyItemBinding
import com.ulearning.ulearning_app.domain.model.Course

class CourseRecentlyAdapter constructor(
    private val courses: List<Course>
) : RecyclerView.Adapter<CourseRecentlyAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_courses_recently_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(courses[position], position)
    }

    override fun getItemCount(): Int = courses.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ListCoursesRecentlyItemBinding.bind(view)


        fun bind(model: Course, position: Int) {


        }

    }
}