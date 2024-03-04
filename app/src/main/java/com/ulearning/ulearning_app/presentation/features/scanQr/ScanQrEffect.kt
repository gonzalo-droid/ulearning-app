package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.UiEffect

sealed class ScanQrEffect : UiEffect {
    object Logout : ScanQrEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : ScanQrEffect()
}
