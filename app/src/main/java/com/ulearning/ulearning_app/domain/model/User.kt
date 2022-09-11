package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class User(
    var address: String?= null,
    var date_of_birth: String?= "",
    var document_number: String?= "",
    var document_type: String?= "",
    var email: String?= "",
    var first_name: String?= "",
    var gender: String?= "",
    var id: Int?= 0,
    var last_name: String?= "",
    var name: String?= "",
    var phone: String?= "",
    var phone_code: String?= null,
    var role: String?= "",
    var second_last_name: String?= "",
) : Serializable