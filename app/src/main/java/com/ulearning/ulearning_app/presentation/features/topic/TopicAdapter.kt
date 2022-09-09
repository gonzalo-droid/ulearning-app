package com.ulearning.ulearning_app.presentation.features.topic

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemTopicBinding
import com.ulearning.ulearning_app.domain.model.Topic

class TopicAdapter constructor(
    private val topics: List<Topic>
) : RecyclerView.Adapter<TopicAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_topic, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(topics[position])
    }

    override fun getItemCount(): Int = topics.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemTopicBinding.bind(view)


        fun bind(model: Topic) {
            Log.d("TOPIC", model.parentId.toString())
            binding.titleText.text  = model.title

            val icon = if( model.parentId == null ) R.drawable.ic_flag  else R.drawable.ic_check_circle

            binding.topicImage.setImageResource(icon)
        }
    }
}