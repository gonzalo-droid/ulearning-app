package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.DoLogoutUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val doLogoutUseCase: DoLogoutUseCase,
    private val getTokenUseCase: GetTokenUseCase,
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
        }
    }

    private fun scanQr() {
        setState { ProfileState.ScanQr }
    }

    private fun goPayment() {
        getTokenUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleToken)
        }
    }

    private fun logout() {
        setState { ProfileState.Loading }

        doLogoutUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleLogout)
        }
    }

    private fun getProfile() {
        setState { ProfileState.Loading }

        getProfileUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleProfile)
        }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { ProfileEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleProfile(data: Profile) {
        setState { ProfileState.DatProfile(data = data) }
    }

    private fun handleLogout(value: Boolean) {
        setEffect { ProfileEffect.Logout }
    }

    private fun handleToken(url: String) {
        setEffect { ProfileEffect.GoToWebView(url = url) }
    }

    companion object Events {
        val dataProfileClicked = ProfileEvent.DataProfileClicked
        val logoutClick = ProfileEvent.LogoutClick
        val paymentClick = ProfileEvent.PaymentClick
        val scanQRClick = ProfileEvent.ScanQRClick
    }
}
