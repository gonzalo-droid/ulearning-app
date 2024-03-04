package com.ulearning.ulearning_app.presentation.features.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ulearning.ulearning_app.databinding.ItemUserBinding
import com.ulearning.ulearning_app.domain.model.User

@Suppress("UNREACHABLE_CODE")
class SearchUserAdapter constructor(
    private val users: List<User>,
    context: Context,
    resource: Int,
) : ArrayAdapter<User>(
        context,
        resource,
    ) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        return super.getView(position, convertView, parent)

        var listItem = convertView

        if (listItem == null) {
            listItem =
                LayoutInflater.from(parent.context)
                    .inflate(com.ulearning.ulearning_app.R.layout.item_user, parent, false)
        }

        if (listItem != null) {
            val user: User = users[position]
            val binding = listItem.let { ItemUserBinding.bind(it) }

            binding.nameText.text = user.name
            binding.emailText.text = user.email
        }

        return listItem!!
    }
}
