package com.ulearning.ulearning_app.presentation.model.design

import com.ulearning.ulearning_app.core.functional.Failure

interface MessageAlertViewState {

    fun messageFailure(failure: Failure)
}
