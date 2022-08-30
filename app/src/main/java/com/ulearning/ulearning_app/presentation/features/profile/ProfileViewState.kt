package com.ulearning.ulearning_app.presentation.features.profile

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription

interface ProfileViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getProfile(data : Profile)
}