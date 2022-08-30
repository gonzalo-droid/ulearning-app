package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class ProfileState : UiState {

    object Idle : ProfileState()

    object Loading : ProfileState()

    data class DatProfile constructor(val data: Profile) : ProfileState()

}