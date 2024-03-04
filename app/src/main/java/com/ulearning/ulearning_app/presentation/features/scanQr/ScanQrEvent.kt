package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class ScanQrEvent : UiEvent {
    object DataProfileClicked : ScanQrEvent()

    object LogoutClick : ScanQrEvent()

    object PaymentClick : ScanQrEvent()

    object ScanQRClick : ScanQrEvent()
}
