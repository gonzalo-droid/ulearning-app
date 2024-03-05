package com.ulearning.ulearning_app.presentation.features.profile

import android.util.Log
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.DoLogoutUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.domain.useCase.profile.UpdateProfileUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val doLogoutUseCase: DoLogoutUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val updatedProfileUseCase: UpdateProfileUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {
    override fun createInitialState(): ProfileState {
        return ProfileState.Idle
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.DataProfileClicked -> getProfile()
            ProfileEvent.LogoutClick -> logout()
            ProfileEvent.PaymentClick -> goPayment()
            ProfileEvent.ScanQRClick -> scanQr()
            else -> {}
        }
    }

    fun updatedProfile(profile: ProfileRequest) {
        setState { ProfileState.Loading }

        updatedProfileUseCase(
            UpdateProfileUseCase.Params(profile)
        ) {
            it.either(::handleFailure, ::handleProfileUpdate)
        }
    }

    private fun scanQr() {
        setState { ProfileState.ScanQr }
    }

    private fun goPayment() {
        getTokenUseCase(
            BaseUseCase.None(),
        ) {
            it.either(::handleFailure, ::handleToken)
        }
    }

    private fun logout() {
        setState { ProfileState.Loading }
        doLogoutUseCase(
            BaseUseCase.None(),
        ) {
            it.either(::handleFailure, ::handleLogout)
        }
    }

    private fun getProfile() {
        setState { ProfileState.Loading }

        getProfileUseCase(
            BaseUseCase.None(),
        ) {
            it.either(::handleFailure, ::handleProfile)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { ProfileEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleProfileUpdate(data: Profile) {
        setState { ProfileState.SuccessMessage }
        setState { ProfileState.DatProfile(data = data) }
    }
    private fun handleProfile(data: Profile) {
        setState { ProfileState.DatProfile(data = data) }
    }

    private fun handleLogout(value: Boolean) {
        setEffect { ProfileEffect.Logout }
    }

    private fun handleToken(url: String) {
        // TODO() REMOVER AL ENVIAR A PROD
        Log.d("urlWebView token", url)
        val sandbox = url.replace("student.ulearning", "sandbox.student.ulearning")
        Log.d("urlWebView sandbox", sandbox)
        setEffect { ProfileEffect.GoToWebView(url = sandbox) }
    }

    companion object Events {
        val dataProfileClicked = ProfileEvent.DataProfileClicked
        val logoutClick = ProfileEvent.LogoutClick
        val paymentClick = ProfileEvent.PaymentClick
        val scanQRClick = ProfileEvent.ScanQRClick
    }
}
