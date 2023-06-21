package com.ulearning.ulearning_app.presentation.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemCoursesBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
import com.ulearning.ulearning_app.presentation.utils.imageLoader.ImageLoaderGlide

class CoursePackageItemAdapter constructor(
    private val items: List<LearningPackageItem> = arrayListOf(),
    private val percentages: List<CoursePercentage>? = listOf(),
    private val onClickListener: (LearningPackageItem) -> Unit,
) : RecyclerView.Adapter<CoursePackageItemAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_courses, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position], onClickListener)
    }

    override fun getItemCount(): Int = items.size

    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemCoursesBinding.bind(view)

        fun bind(model: LearningPackageItem, onClickListener: (LearningPackageItem) -> Unit) {

            val coursePercentage: CoursePercentage? = percentages?.firstOrNull { it.courseId == model.courseId }
            val value = !coursePercentage?.percentage.isNullOrEmpty()
            val valueString = if (value) coursePercentage?.percentage!! else "0"
            val valueInt = if (value) coursePercentage?.percentage?.toDouble()!!.toInt() else 0

            binding.progressSnackBar.progress = valueInt
            binding.percentageText.text = "$valueString %"

            binding.requiredText.visibility = if (model.isRequired) View.VISIBLE else View.GONE

            binding.categoryText.text = model.course?.category?.name

            binding.titleText.text = model.course?.title

            model.course?.mainImage?.originalUrl?.let {
                ImageLoaderGlide().loadImage(
                    imageView = binding.imageIv,
                    imagePath = it,
                    requestOptions = RequestOptions.centerCropTransform(),
                    placeHolder = R.drawable.course_test
                )
            }

            itemView.setOnClickListener {
                onClickListener(model)
            }
        }
    }
}
