package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class ValidateCertEvent : UiEvent {
    object VerifyCertClick : ValidateCertEvent()
}
