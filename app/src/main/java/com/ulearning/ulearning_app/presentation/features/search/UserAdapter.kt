package com.ulearning.ulearning_app.presentation.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemUserBinding
import com.ulearning.ulearning_app.domain.model.User

class UserAdapter constructor(
    private var users: List<User>,
    private val onClickListener: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(users[position], onClickListener)
    }

    fun updateUser(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemUserBinding.bind(view)

        fun bind(model: User, onClickListener: (User) -> Unit) {

            binding.nameText.text = model.name

            binding.emailText.text = model.email

            itemView.setOnClickListener {
                onClickListener(model)
            }
        }
    }
}
