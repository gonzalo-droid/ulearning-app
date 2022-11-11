package com.ulearning.ulearning_app.presentation.features.conversation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dateFormat
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ItemConversationBinding
import com.ulearning.ulearning_app.domain.model.Conversation

class ConversationAdapter constructor(
    private val conversations: List<Conversation>,
    private val onClickListener: (Conversation) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_conversation, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(conversations[position], onClickListener)
    }

    override fun getItemCount(): Int = conversations.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemConversationBinding.bind(view)


        fun bind(model: Conversation, onClickListener: (Conversation) -> Unit) {

            binding.titleText.text  = model.firstMessage?.content

            binding.timeText.text = model.firstMessage?.publishedAt?.dateFormat(Config.DATE_FORMAT_THREE)?.dateFormat(Config.DATE_FORMAT_NINETEEN)

            itemView.setOnClickListener {
                onClickListener(model)
            }

        }

    }
}