package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Teacher(
    //val avatar: Any? = null,
    val firstName: String? = "",
    val id: Int? = 0,
    val lastName: String? = "",
    val subtype: String? = "",
    val type: String? = ""
) : Serializable {

    fun formatSubtype(): String {
        return when (this.subtype) {
            "tutor" -> "Tutor"
            "instructor" -> "Instructor"
            "facilitator" -> "Facilitador"
            "academic_tutor" -> "Tutor académico"
            "umbrella_tutor" -> "Tutor sombrilla"
            "administrative_tutor" -> "Tutor administrativo"
            "teacher" -> "Docente"
            else -> "Docente"
        }
    }
}