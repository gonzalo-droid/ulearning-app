package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem

interface ValidateCertViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun validateCert(file: FileItem)

    fun downloadFilePDF(file: DownloadFile)
}
