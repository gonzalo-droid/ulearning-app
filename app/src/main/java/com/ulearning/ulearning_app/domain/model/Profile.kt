package com.ulearning.ulearning_app.domain.model

data class Profile(
    val address: String? = "",
    val avatar: Any? = null,
    val country: Any? = null,
    val countryId: Int? = 0,
    val dateOfBirth: String? = "",
    val documentNumber: String? = "",
    val documentType: String? = "",
    val email: String? = "",
    val firstName: String? = "",
    val gender: String? = "",
    val id: Int? = 0,
    val lastName: String? = "",
    val name: String? = "",
    val permissions: List<String?>? = arrayListOf(),
    val phone: String? = "",
    val phoneCode: String? = "",
    val plan: Any? = null,
    val role: String? = "",
    val secondLastName: String? = "",
    val suspendedAt: String? = ""
)
