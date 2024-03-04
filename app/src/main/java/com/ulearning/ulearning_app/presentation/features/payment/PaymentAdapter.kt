package com.ulearning.ulearning_app.presentation.features.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemPaymentBinding
import com.ulearning.ulearning_app.domain.model.Payment

class PaymentAdapter(
    private val items: List<Payment> = arrayListOf(),
    private val onClickListener: (Payment) -> Unit,
) : RecyclerView.Adapter<PaymentAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false),
        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int,
    ) {
        holder.bind(items[position], onClickListener)
    }

    override fun getItemCount(): Int = items.size

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPaymentBinding.bind(view)

        fun bind(
            model: Payment,
            onClickListener: (Payment) -> Unit,
        ) {
            binding.amountTv.text = model.moneyFormat()
            binding.statusTv.text = model.statusFormat()

            itemView.setOnClickListener {
                onClickListener(model)
            }
        }
    }
}
