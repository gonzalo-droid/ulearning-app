package com.ulearning.ulearning_app.presentation.features.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemConversationBinding
import com.ulearning.ulearning_app.domain.model.Message

class MessageAdapter constructor(
    private val messages: List<Message>
) : RecyclerView.Adapter<MessageAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_conversation, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemConversationBinding.bind(view)


        fun bind(model: Message) {

            //binding.titleText.text  = model.firstMessage?.content

        }

    }
}