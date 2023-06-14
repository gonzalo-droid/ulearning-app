package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Message(
    val classification: String? = "",
    val content: String? = "",
    val id: Int? = 0,
    val publishedAt: String,
    val sendBy: User = User(),
    val status: String? = "",
    val subject: Any? = null,
    val type: String? = "",
    val userIds: List<String?>? = arrayListOf(),
    val uuid: String? = ""
) : Serializable
