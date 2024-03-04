package com.ulearning.ulearning_app.presentation.model.design

import com.ulearning.ulearning_app.R

class MessageDesign private constructor(
    val idMessage: Int = R.string.error_user_message,
    val state: Int?,
) {
    data class Builder(
        var idMessage: Int = R.string.error_user_message,
        var state: Int? = null,
    ) {
        fun idMessage(idMessage: Int) = apply { this.idMessage = idMessage }

        fun state(state: Int) = apply { this.state = state }

        fun build() = MessageDesign(idMessage, state)
    }
}
