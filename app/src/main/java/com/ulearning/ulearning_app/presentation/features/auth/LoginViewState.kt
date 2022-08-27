package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.core.functional.Failure

interface LoginViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun homeActivity()

}