package com.ulearning.ulearning_app.presentation.features.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dateFormat
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ItemMessageReciverBinding
import com.ulearning.ulearning_app.databinding.ItemMessageSendBinding
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.Message

class MessageAdapter constructor(
    private val messages: List<Message>,
    private val conversation: Conversation
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MESSAGE_SEND = 1
        const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_MESSAGE_SEND) {
            SendViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_send, parent, false)
            )
        } else {
            ReceiverViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_reciver, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message: Message = messages[position]

        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SEND -> (holder as SendViewHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceiverViewHolder).bind(message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = messages[position]
        conversation
        return if (message.sendBy.id == conversation.firstMessage?.sendBy?.id) {
            VIEW_TYPE_MESSAGE_SEND
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun getItemCount(): Int = messages.size

    inner class ReceiverViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemMessageReciverBinding.bind(view)


        fun bind(model: Message) {

            binding.titleText.text = model.content
            binding.nameText.text = model.sendBy.name

            val time = model.publishedAt
            binding.timeText.text =
                time?.dateFormat(Config.DATE_FORMAT_THREE)?.dateFormat(Config.DATE_FORMAT_THIRTY)

        }

    }

    inner class SendViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemMessageSendBinding.bind(view)


        fun bind(model: Message) {

            binding.titleText.text = model.content
            binding.nameText.text = model.sendBy.name

            val time = model.publishedAt
            binding.timeText.text =
                time?.dateFormat(Config.DATE_FORMAT_THREE)?.dateFormat(Config.DATE_FORMAT_THIRTY)

        }


    }
}