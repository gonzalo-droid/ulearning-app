package com.ulearning.ulearning_app.presentation.features.scanQr

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.useCase.auth.DoLogoutUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanQrViewModel
    @Inject
    constructor(
        private val getProfileUseCase: GetProfileUseCase,
        private val doLogoutUseCase: DoLogoutUseCase,
        private val getTokenUseCase: GetTokenUseCase,
    ) : BaseViewModel<ScanQrEvent, ScanQrState, ScanQrEffect>() {
        override fun createInitialState(): ScanQrState {
            return ScanQrState.Idle
        }

        override fun handleEvent(event: ScanQrEvent) {
            when (event) {
                else -> {}
            }
        }

        private fun handleFailure(failure: Failure) {
            setEffect { ScanQrEffect.ShowMessageFailure(failure = failure) }
        }

        companion object Events {
        }
    }
