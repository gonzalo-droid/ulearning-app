package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.presentation.base.UiEvent


sealed class ProfileEvent : UiEvent {

    object DataProfileClicked : ProfileEvent()

    object OnLogoutClick : ProfileEvent()

}