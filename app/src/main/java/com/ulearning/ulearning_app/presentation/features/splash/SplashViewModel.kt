package com.ulearning.ulearning_app.presentation.features.splash

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetSessionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        private val getSessionUseCase: GetSessionUseCase,
    ) : BaseViewModel<SplashEvent, SplashState, SplashEffect>() {
        override fun createInitialState(): SplashState {
            return SplashState.Idle
        }

        override fun handleEvent(event: SplashEvent) {
            when (event) {
                SplashEvent.GotoActivity -> verifyToken()
            }
        }

        private fun verifyToken() =
            getSessionUseCase(BaseUseCase.None()) {
                it.either(::handleFailure, ::handleSession)
            }

        private fun handleFailure(failure: Failure) {
            setEffect { SplashEffect.ShowMessageFailure(failure = failure) }
        }

        private fun handleSession(success: Boolean) {
            if (success) {
                setEffect { SplashEffect.GoToHome }
            } else {
                setEffect { SplashEffect.GoToLogin }
            }
        }
    }
