package com.ulearning.ulearning_app.presentation.features.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityCourseProgressBinding
import com.ulearning.ulearning_app.databinding.ActivityPaymentBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.courseDetail.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CourseProgressEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CourseProgressReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseProgressViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseProgressViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity :
    BaseActivityWithViewModel<ActivityPaymentBinding, PaymentViewModel>(),
    PaymentViewState {

    override val binding: ActivityPaymentBinding by dataBinding(
        ActivityPaymentBinding::inflate
    )

    override val viewModel: PaymentViewModel by viewModels()

    override val dataBindingViewModel = BR.paymentViewModel

    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PaymentReducer.instance(viewState = this)

        binding.topBarSimple.btnBack.setOnClickListener {
            finish()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(this@PaymentActivity)

        binding.noDataInclude.root.visibility = View.GONE
        binding.recycler.visibility = View.INVISIBLE
        binding.skeletonInclude.root.visibility = View.VISIBLE

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(PaymentEvent.PaymentClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@PaymentActivity, method = {
                state.collect { state ->
                    PaymentReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@PaymentActivity, method = {
                effect.collect { effect ->
                    PaymentReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun messageFailure(failure: Failure) {
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        binding.skeletonInclude.root.visibility = View.VISIBLE
    }

    override fun getPayments(payments: List<Payment>) {
        binding.skeletonInclude.root.visibility = View.INVISIBLE

        if (payments.isEmpty()) {
            binding.recycler.visibility = View.INVISIBLE
            binding.noDataInclude.root.visibility = View.VISIBLE
        } else {
            binding.noDataInclude.root.visibility = View.GONE
            recycler.adapter =
                PaymentAdapter(
                    items = payments,
                ) { model ->
                    onItemSelected(model)
                }
            binding.recycler.visibility = View.VISIBLE
        }
    }


    private fun onItemSelected(model: Payment) {
        val bottomSheetFragment = BottomSheetPaymentItemFragment.newInstance(model)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}