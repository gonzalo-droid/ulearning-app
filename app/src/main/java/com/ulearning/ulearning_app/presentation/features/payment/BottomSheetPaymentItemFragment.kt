package com.ulearning.ulearning_app.presentation.features.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ulearning.ulearning_app.databinding.FragmentBottomSheetItemPaymentBinding
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.PaymentItem

class BottomSheetPaymentItemFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetItemPaymentBinding? = null
    private val binding get() = _binding!!

    lateinit var payment: Payment

    private lateinit var recycler: RecyclerView

    companion object {
        private const val PAYMENT = "payment"

        @JvmStatic
        fun newInstance(payment: Payment): BottomSheetPaymentItemFragment =
            BottomSheetPaymentItemFragment().apply {
                arguments =
                    Bundle().apply {
                        putSerializable(PAYMENT, payment)
                    }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomSheetItemPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            payment = it.getSerializable(PAYMENT) as Payment
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireContext())

        payment.let {
            if (it.items.isNotEmpty()) getItemsPayment(it.items)
        }
        // Access views using binding
        // Use myObjects in your bottom sheet dialog as needed
    }

    fun getItemsPayment(payments: List<PaymentItem>) {
        recycler.adapter =
            PaymentItemAdapter(
                items = payments,
            ) { model ->
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
