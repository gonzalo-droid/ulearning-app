package com.ulearning.ulearning_app.presentation.features.search


object SearchReducer {

    private lateinit var viewState: SearchViewState

    fun instance(viewState: SearchViewState) {
        this.viewState = viewState
    }

    fun selectState(state: SearchState) {
        when (state) {
            is SearchState.Idle -> {}

            is SearchState.Loading -> viewState.loading()
            is SearchState.DataConversation -> viewState.conversation(state.conversation)
            is SearchState.UserList -> viewState.users(state.users)
        }
    }

    fun selectEffect(effect: SearchEffect) {
        when (effect) {
            is SearchEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)

        }
    }
}