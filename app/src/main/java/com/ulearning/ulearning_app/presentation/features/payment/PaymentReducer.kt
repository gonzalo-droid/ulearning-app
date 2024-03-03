package com.ulearning.ulearning_app.presentation.features.payment

import com.ulearning.ulearning_app.presentation.features.home.HomeEffect
import com.ulearning.ulearning_app.presentation.features.home.state.CourseProgressState
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseProgressViewState

object PaymentReducer {

    private lateinit var viewState: PaymentViewState

    fun instance(viewState: PaymentViewState) {
        PaymentReducer.viewState = viewState
    }

    fun selectState(state: PaymentState) {
        when (state) {
            is PaymentState.Idle -> {}

            is PaymentState.Loading -> viewState.loading()

            is PaymentState.PaymentList -> {
                viewState.getPayments(payments = state.items)
            }

            else -> {}
        }
    }

    fun selectEffect(effect: PaymentEffect) {
        when (effect) {
            is PaymentEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            else -> {}
        }
    }
}
