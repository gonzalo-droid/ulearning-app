package com.ulearning.ulearning_app.presentation.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemCoursesBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.utils.imageLoader.ImageLoaderGlide

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

            binding.progressSnackBar.visibility = if (model.isFinished!!) View.INVISIBLE else View.VISIBLE
            binding.percentageText.visibility = if (model.isFinished!!) View.INVISIBLE else View.VISIBLE

            model.course?.mainImage?.originalUrl?.let {
                ImageLoaderGlide().loadImage(
                    imageView = binding.imageIv,
                    imagePath = it,
                    requestOptions = RequestOptions.centerCropTransform(),
                    placeHolder = R.drawable.course_test
                )
            }


            var coursePercentage: CoursePercentage? = null
            var valueString = "0"
            var valueInt = 0

            percentages?.let { per ->
                coursePercentage = per.firstOrNull { it.courseId == model.courseId }

                if (!coursePercentage?.percentage.isNullOrEmpty()) {
                    valueString = coursePercentage?.percentage!!
                    valueInt = coursePercentage?.percentage!!.toDouble().toInt()
                }
                binding.progressSnackBar.progress = valueInt
            }

            binding.categoryText.text = model.course?.category?.name

            binding.percentageText.text = "$valueString %"

            binding.titleText.text = model.course!!.title

            itemView.setOnClickListener {
                onClickListener(model)
            }
        }
    }
}
