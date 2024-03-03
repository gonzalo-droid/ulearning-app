package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Category(
    var cardImage: CardImage? = null,
    var color: String? = "",
    var description: String? = "",
    var id: Int? = 0,
    var name: String? = "",
    var type: String? = ""
) : Serializable
