package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    override fun createInitialState(): LoginState {
        return LoginState.Idle
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LoginClicked -> doLogin()
            LoginEvent.RecoveredPasswordClicked -> goRecoveryPassword()
        }
    }

    private fun goRecoveryPassword() {

    }

    private fun doLogin() {
        setState {  LoginState.Loading }
        doLoginUseCase(DoLoginUseCase.Params(email = email.value, password = password.value)) {
            it.either(::handleFailure, ::handleLogin)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { LoginEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleLogin(success: Boolean) {
        setState { LoginState.LoginSuccess(success = success) }
    }

    companion object Events {
        val loginClicked = LoginEvent.LoginClicked
        val recoveredPasswordClicked = LoginEvent.RecoveredPasswordClicked
    }
}