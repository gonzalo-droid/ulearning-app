package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

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

    val items: List<LearningPackageItem>? = arrayListOf(),
) : Serializable
