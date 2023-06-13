package com.ulearning.ulearning_app.domain.model


data class LearningPackage(

    var amount: String?,

    var code: String? = "",

    var descriptionLarge: String? = "",

    var descriptionShort: String? = "",

    var id: Int?,

    var isShop: Boolean?,

    var mainImage: MainImage? = null,

    var title: String? = "",

    var type: String? = "",
)