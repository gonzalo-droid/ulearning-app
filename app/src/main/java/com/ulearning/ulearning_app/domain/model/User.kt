package com.ulearning.ulearning_app.domain.model

data class User(
    var address: Any = Any(),
    var avatar: Any = Any(),
    var country: Country = Country(),
    var country_id: Int = 0,
    var date_of_birth: String = "",
    var document_number: String = "",
    var document_type: String = "",
    var email: String = "",
    var first_name: String = "",
    var gender: String = "",
    var id: Int = 0,
    var last_name: String = "",
    var name: String = "",
    var permissions: List<String> = listOf(),
    var phone: String = "",
    var phone_code: Any = Any(),
    var plan: Plan = Plan(),
    var role: String = "",
    var second_last_name: String = "",
    var suspended_at: Any = Any()
)