package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class ProfileEvent : UiEvent {
    object DataProfileClicked : ProfileEvent()
    object LogoutClick : ProfileEvent()
    object PaymentClick : ProfileEvent()
    object ScanQRClick : ProfileEvent()
    object Updatedlick : ProfileEvent()
}
