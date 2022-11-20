package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription

interface ValidateCertViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun validateCert(file: FileItem)
}