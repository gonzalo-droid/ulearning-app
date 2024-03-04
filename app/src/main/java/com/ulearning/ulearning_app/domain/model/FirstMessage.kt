package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class FirstMessage(
    val classification: String? = "",
    val content: String? = "",
    val id: Int? = 0,
    val parentId: Any? = null,
    val publishedAt: String? = "",
    val sendBy: User? = null,
    val status: String? = "",
    val subject: Any? = null,
    val type: String? = "",
    val userIds: List<String?>? = arrayListOf(),
    val uuid: String? = "",
) : Serializable
