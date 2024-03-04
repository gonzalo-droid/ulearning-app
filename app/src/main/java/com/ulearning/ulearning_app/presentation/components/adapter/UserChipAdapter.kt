package com.ulearning.ulearning_app.presentation.components.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemChipUserBinding
import com.ulearning.ulearning_app.domain.model.User

class UserChipAdapter constructor(
    private val users: List<User>,
) : RecyclerView.Adapter<UserChipAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chip_user, parent, false),
        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int,
    ) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemChipUserBinding.bind(view)

        fun bind(model: User) {
            binding.nameTitle.text = model.name
        }
    }
}
