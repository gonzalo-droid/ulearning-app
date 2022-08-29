package com.ulearning.ulearning_app.domain.model

data class CardImage(
    var originalUrl: String?= "",
    var previewUrl: String?= "",
    var responsiveImages: List<Any>?= arrayListOf(),
)