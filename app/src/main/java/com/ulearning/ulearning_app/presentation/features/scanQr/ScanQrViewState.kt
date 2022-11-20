package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription

interface ScanQrViewState {

    fun messageFailure(failure: Failure)

    fun loading()
}