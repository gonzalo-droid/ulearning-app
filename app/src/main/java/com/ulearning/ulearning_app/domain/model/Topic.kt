package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Topic(
    val children: List<Topic>? = arrayListOf(),
    val courseId: Int? = 0,
    val id: Int? = 0,
    val order: Int? = 0,
    val parentId: Int? = null,
    val title: String? = "",
    val type: String? = ""
) : Serializable
