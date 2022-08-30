package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetProfileUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.GetCoursesSubscriptionUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun createInitialState(): ProfileState {
        return ProfileState.Idle
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.DataProfileClicked -> getProfile()

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



    companion object Events {
        val dataProfileClicked = ProfileEvent.DataProfileClicked
    }
}