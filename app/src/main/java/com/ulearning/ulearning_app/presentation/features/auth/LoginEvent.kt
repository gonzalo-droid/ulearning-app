package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.presentation.base.UiEvent

sealed class LoginEvent : UiEvent {
    object LoginClicked : LoginEvent()

    object LoginGoogleClicked : LoginEvent()

    object LoginFacebookClicked : LoginEvent()
}
