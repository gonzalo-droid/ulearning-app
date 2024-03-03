package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class MainImage(
    var originalUrl: String? = "",
    var previewUrl: String? = "",
    var responsiveImages: List<Any>? = arrayListOf(),
    var size: Int? = 0,
    var uuid: String? = ""
) : Serializable
