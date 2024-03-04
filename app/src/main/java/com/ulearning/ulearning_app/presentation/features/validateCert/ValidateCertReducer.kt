package com.ulearning.ulearning_app.presentation.features.validateCert

object ValidateCertReducer {
    private lateinit var viewState: ValidateCertViewState

    fun instance(viewState: ValidateCertViewState) {
        this.viewState = viewState
    }

    fun selectState(state: ValidateCertState) {
        when (state) {
            is ValidateCertState.Idle -> {}

            is ValidateCertState.Loading -> viewState.loading()
            is ValidateCertState.ValidateCert -> viewState.validateCert(state.file)

            is ValidateCertState.DownloadFilePDF -> viewState.downloadFilePDF(file = state.file)
        }
    }

    fun selectEffect(effect: ValidateCertEffect) {
        when (effect) {
            is ValidateCertEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
        }
    }
}
