package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.useCase.courses.GetDownloadShowGuestFileUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetShowGuestFileUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ValidateCertViewModel
    @Inject
    constructor(
        private val getShowGuestFileUseCase: GetShowGuestFileUseCase,
        private val getDownloadShowGuestFileUseCase: GetDownloadShowGuestFileUseCase,
    ) : BaseViewModel<ValidateCertEvent, ValidateCertState, ValidateCertEffect>() {
        var numberCert: String = ""
        var codeCert: String = ""
        lateinit var file: DownloadFile

        override fun createInitialState(): ValidateCertState {
            return ValidateCertState.Idle
        }

        override fun handleEvent(event: ValidateCertEvent) {
            when (event) {
                ValidateCertEvent.VerifyCertClick -> sendValidateCert()
            }
        }

        private fun sendValidateCert() {
            if (numberCert.isNotEmpty() && codeCert.isEmpty()) {
                setState { ValidateCertState.Loading }
                getShowGuestFileUseCase(
                    GetShowGuestFileUseCase.Params(name = numberCert),
                ) {
                    it.either(::handleFailure, ::handleValidateCert)
                }
            } else if (numberCert.isNotEmpty() && codeCert.isNotEmpty()) {
                setState { ValidateCertState.Loading }
                getDownloadShowGuestFileUseCase(
                    GetDownloadShowGuestFileUseCase.Params(
                        name = numberCert.trim(),
                        hash = codeCert.trim(),
                    ),
                ) {
                    it.either(::handleFailure, ::handleDownloadShowGuest)
                }
            } else {
                setEffect { ValidateCertEffect.ShowMessageFailure(failure = Failure.DefaultError(R.string.number_cert_error)) }
            }
        }

        private fun handleFailure(failure: Failure) {
            setEffect { ValidateCertEffect.ShowMessageFailure(failure = failure) }
        }

        private fun handleValidateCert(file: FileItem) {
            setState { ValidateCertState.ValidateCert(file = file) }
        }

        private fun handleDownloadShowGuest(file: DownloadFile) {
            setState { ValidateCertState.DownloadFilePDF(file = file) }
        }

        companion object Events {
            val verifyCodeCert = ValidateCertEvent.VerifyCertClick
        }
    }
