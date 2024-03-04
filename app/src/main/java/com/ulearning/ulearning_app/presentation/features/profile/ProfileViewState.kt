package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile

interface ProfileViewState {
    fun messageFailure(failure: Failure)

    fun loading()

    fun getProfile(data: Profile)

    fun goToWebView(url: String)

    fun logout()

    fun scanQr()
}
