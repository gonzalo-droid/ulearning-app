package com.ulearning.ulearning_app.domain.model

data class FileInfo(
    val date: String? = "",
    val email: String? = "",
    val hours: String? = "",
    val name: String? = "",
    val period: Any? = Any(),
    val rating: Int? = 0,
    val sendMail: Boolean? = false,
    val title: String? = "",
    val topics: String? = ""
)