package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class ValidateCertState : UiState {

    object Idle : ValidateCertState()

    object Loading : ValidateCertState()

    data class ValidateCert constructor(val file: FileItem) : ValidateCertState()

}