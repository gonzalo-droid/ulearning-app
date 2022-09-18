package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.SendFCMTokenUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.model.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
    private val sendFCMTokenUseCase: SendFCMTokenUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    val user = User()

    override fun createInitialState(): LoginState {
        return LoginState.Idle
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LoginClicked -> verifyLogin()
            LoginEvent.RecoveredPasswordClicked -> goRecoveryPassword()
        }
    }

    private fun goRecoveryPassword() {

    }

    private fun verifyLogin() {

        user.verifyLogin().let {

            if (it.first) {

                serviceFCM()

            } else {
                setEffect { LoginEffect.ShowMessageFailure(failure = it.second!!) }
            }
        }

    }

    private fun serviceFCM() {
        user.serviceTokenFirebase(response = { firebaseToken ->

            user.fcmToken = firebaseToken

            doLogin()

        }, error = { error ->

            setEffect { LoginEffect.ShowMessageFailure(failure = error) }
        })
    }

    private fun doLogin() {

        setState { LoginState.Loading }

        doLoginUseCase(
            DoLoginUseCase.Params(
                email = user.email, password = user.password
            )
        ) {
            it.either(::handleFailure, ::handleLogin)
        }

    }

    private fun sendFCMToken(){
        sendFCMTokenUseCase(
            SendFCMTokenUseCase.Params(
                fcmToken = user.fcmToken
            )
        ) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { LoginEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleLogin(success: Boolean) {
        sendFCMToken()
    }

    private fun handleSuccess(success: Boolean) {
        setState { LoginState.LoginSuccess(success = success) }
    }

    companion object Events {
        val loginClicked = LoginEvent.LoginClicked
        val recoveredPasswordClicked = LoginEvent.RecoveredPasswordClicked
    }
}