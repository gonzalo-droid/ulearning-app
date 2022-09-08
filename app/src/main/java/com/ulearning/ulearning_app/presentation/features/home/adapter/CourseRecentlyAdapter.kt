package com.ulearning.ulearning_app.presentation.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ListCoursesRecentlyItemBinding
import com.ulearning.ulearning_app.domain.model.Subscription

class CourseRecentlyAdapter constructor(
    private val courses: List<Subscription>,
    private val onClickListener: (Subscription) -> Unit
) : RecyclerView.Adapter<CourseRecentlyAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_courses_recently_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(courses[position], onClickListener)
    }

    override fun getItemCount(): Int = courses.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ListCoursesRecentlyItemBinding.bind(view)


        fun bind(model: Subscription, onClickListener: (Subscription) -> Unit) {

            //binding.progressSnackBar.progress  = model.course!!.category!!.name

            binding.titleText.text = model.course!!.title

            itemView.setOnClickListener {
                onClickListener(model)
            }

        }

    }
}