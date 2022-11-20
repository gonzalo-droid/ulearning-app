package com.ulearning.ulearning_app.presentation.features.validateCert

import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.useCase.auth.DoLogoutUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetMyRecordUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetShowGuestFileUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseEvent
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseState
import com.ulearning.ulearning_app.presentation.model.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ValidateCertViewModel
@Inject constructor(
    private val getShowGuestFileUseCase: GetShowGuestFileUseCase,
) : BaseViewModel<ValidateCertEvent, ValidateCertState, ValidateCertEffect>() {

    var numberCert: String = ""
    var codeCert = MutableStateFlow("")

    override fun createInitialState(): ValidateCertState {
        return ValidateCertState.Idle
    }

    override fun handleEvent(event: ValidateCertEvent) {
        when (event) {
            ValidateCertEvent.VerifyCertClick -> sendValidateCert()
        }
    }

    private fun sendValidateCert() {
        if (numberCert.isNotEmpty()) {
            setState { ValidateCertState.Loading }
            getShowGuestFileUseCase(
                GetShowGuestFileUseCase.Params(name = numberCert)
            ) {
                it.either(::handleFailure, ::handleValidateCert)
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

    companion object Events {
        val verifyCodeCert = ValidateCertEvent.VerifyCertClick
    }
}