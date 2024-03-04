package com.ulearning.ulearning_app.presentation.features.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.databinding.ItemDetailPaymentBinding
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.PaymentItem

class PaymentItemAdapter(
    private val items: List<PaymentItem> = arrayListOf(),
    private val onClickListener: (Payment) -> Unit,
) : RecyclerView.Adapter<PaymentItemAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_payment, parent, false),
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
        val binding = ItemDetailPaymentBinding.bind(view)

        fun bind(
            model: PaymentItem,
            onClickListener: (Payment) -> Unit,
        ) {
            binding.amountTv.text = model.moneyFormat()
            binding.titleTv.text = model.title
        }
    }
}
