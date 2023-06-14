package com.ulearning.ulearning_app.presentation.features.scanQr

object ScanQrReducer {

    private lateinit var viewState: ScanQrViewState

    fun instance(viewState: ScanQrViewState) {
        this.viewState = viewState
    }

    fun selectState(state: ScanQrState) {
        when (state) {
            is ScanQrState.Idle -> {}

            is ScanQrState.Loading -> viewState.loading()
        }
    }

    fun selectEffect(effect: ScanQrEffect) {
        when (effect) {
            is ScanQrEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
        }
    }
}
