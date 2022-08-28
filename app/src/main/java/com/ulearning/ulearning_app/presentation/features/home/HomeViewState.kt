package com.ulearning.ulearning_app.presentation.features.home

import com.ulearning.ulearning_app.core.functional.Failure

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun homeActivity()

}