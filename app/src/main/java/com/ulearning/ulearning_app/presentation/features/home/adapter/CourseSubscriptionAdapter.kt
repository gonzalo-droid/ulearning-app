package com.ulearning.ulearning_app.presentation.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemCoursesBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription

class CourseSubscriptionAdapter constructor(
    private val courses: List<Subscription>,
    private val percentages: List<CoursePercentage>? = listOf(),
    private val onClickListener: (Subscription) -> Unit
) : RecyclerView.Adapter<CourseSubscriptionAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_courses, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(courses[position], onClickListener)
    }

    override fun getItemCount(): Int = courses.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemCoursesBinding.bind(view)


        fun bind(model: Subscription, onClickListener: (Subscription) -> Unit) {

            binding.progressSnackBar.visibility = if(model.isFinished!!) View.INVISIBLE else View.VISIBLE

            percentages.filter

            //binding.progressSnackBar.progress  = model.course!!.category!!.name

            binding.titleText.text = model.course!!.title

            itemView.setOnClickListener {
                onClickListener(model)
            }

        }

    }
}