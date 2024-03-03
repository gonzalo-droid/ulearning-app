package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Plan(
    var id: Int? = 0,
    var type: String? = "",
    var userId: Int? = 0
) : Serializable
