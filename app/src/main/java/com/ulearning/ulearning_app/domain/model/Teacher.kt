package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Teacher(
    val avatar: Any? = null,
    val firstName: String? = "",
    val id: Int? = 0,
    val lastName: String? = "",
    val subtype: String? = "",
    val type: String? = ""
)  : Serializable {

}