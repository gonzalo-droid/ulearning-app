package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class ScanQrState : UiState {

    object Idle : ScanQrState()

    object Loading : ScanQrState()

}