package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.profile.GetPaymentsUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel
    @Inject
    constructor(
        private val getPaymentsUseCase: GetPaymentsUseCase,
    ) : BaseViewModel<PaymentEvent, PaymentState, PaymentEffect>() {
        private val isFinished = true

        private val page = 1

        private val courseIds = arrayListOf<Int>()

        private var listCourseRecent = listOf<Subscription>()

        var userId = 1

        var typeRole: String = ""

        override fun createInitialState(): PaymentState {
            return PaymentState.Idle
        }

        override fun handleEvent(event: PaymentEvent) {
            when (event) {
                PaymentEvent.PaymentClicked -> getPayments()
            }
        }

        private fun getPayments() {
            setState { PaymentState.Loading }

            getPaymentsUseCase(
                GetPaymentsUseCase.Params(page = page),
            ) {
                it.either(::handleFailure, ::handlePayments)
            }
        }

        private fun handleFailure(failure: Failure) {
            setEffect { PaymentEffect.ShowMessageFailure(failure = failure) }
        }

        private fun handlePayments(payments: List<Payment>) {
            setState { PaymentState.PaymentList(items = payments) }
        }

        companion object Events {
        }
    }
