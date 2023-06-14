package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class User(
    var address: String? = null,
    var dateOfBirth: String? = "",
    var documentNumber: String? = "",
    var documentType: String? = "",
    var email: String? = "",
    var firstName: String? = "",
    var gender: String? = "",
    var id: Int? = 0,
    var lastName: String? = "",
    var name: String? = "",
    var phone: String? = "",
    var phoneCode: String? = null,
    var role: String? = "",
    var secondLastName: String? = "",
) : Serializable {

    override fun toString(): String {
        return this.name ?: ""
    }
}
