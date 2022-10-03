package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Country(
    var code: String?= "",
    var id: Int?= 0,
    var name: String?= "",
    var phoneCode: String?= ""
) : Serializable