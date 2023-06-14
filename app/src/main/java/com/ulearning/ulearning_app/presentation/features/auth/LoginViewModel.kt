package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.*
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import com.ulearning.ulearning_app.presentation.model.entity.LoginFacebook
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle
import com.ulearning.ulearning_app.presentation.model.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
    private val sendFCMTokenUseCase: SendFCMTokenUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val doLoginFacebookUseCase: DoLoginFacebookUseCase,
    private val doLoginGoogleUseCase: DoLoginGoogleUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    val user = User()

    override fun createInitialState(): LoginState {
        return LoginState.Idle
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LoginClicked -> verifyLogin()
        }
    }

    private fun loginInFacebook() {
        doLoginUseCase(
            DoLoginUseCase.Params(
                email = user.email, password = user.password
            )
        ) {
            it.either(::handleFailure, ::handleLoginProvider)
        }
    }

    private fun getProfile() {
        getProfileUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleProfile)
        }
    }

    fun sendDataLoginInFacebook(data: LoginFacebook) {

        doLoginFacebookUseCase(
            DoLoginFacebookUseCase.Params(
                data = data
            )
        ) {
            it.either(::handleFailure, ::handleLoginProvider)
        }
    }

    fun sendDataLoginInGoogle(data: LoginGoogle) {

        doLoginGoogleUseCase(
            DoLoginGoogleUseCase.Params(
                data = data
            )
        ) {
            it.either(::handleFailure, ::handleLoginProvider)
        }
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

    private fun sendFCMToken() {
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

    private fun handleLoginProvider(success: Boolean) {

        user.serviceTokenFirebase(response = { firebaseToken ->

            user.fcmToken = firebaseToken

            sendFCMToken()
        }, error = { error ->

            setEffect { LoginEffect.ShowMessageFailure(failure = error) }
        })
    }

    private fun handleLogin(success: Boolean) {
        sendFCMToken()
    }

    private fun handleSuccess(success: Boolean) {
        getProfile()
    }

    private fun handleProfile(data: Profile) {
        setState { LoginState.LoginSuccess(success = true) }
    }

    companion object Events {
        val loginClicked = LoginEvent.LoginClicked
        val loginGoogleClicked = LoginEvent.LoginGoogleClicked
        val loginFacebookClicked = LoginEvent.LoginFacebookClicked
    }
}
