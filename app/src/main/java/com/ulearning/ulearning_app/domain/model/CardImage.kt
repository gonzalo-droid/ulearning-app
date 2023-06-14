package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class CardImage(
    var originalUrl: String? = "",
    var previewUrl: String? = "",
    var responsiveImages: List<Any>? = arrayListOf(),
) : Serializable
