package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.core.functional.Failure

interface ScanQrViewState {
    fun messageFailure(failure: Failure)

    fun loading()
}
