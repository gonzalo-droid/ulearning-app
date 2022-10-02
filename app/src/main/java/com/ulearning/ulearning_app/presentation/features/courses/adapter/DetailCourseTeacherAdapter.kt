package com.ulearning.ulearning_app.presentation.features.courses.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemTeacherBinding
import com.ulearning.ulearning_app.domain.model.Teacher

class DetailCourseTeacherAdapter constructor(
    private val teachers: List<Teacher>,
) : RecyclerView.Adapter<DetailCourseTeacherAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_teacher, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int = teachers.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemTeacherBinding.bind(view)


        fun bind(model: Teacher) {

            binding.nameText.text  = "${model.firstName} ${model.lastName}"

            binding.roleText.text  = model.formatSubtype()


        }

    }
}