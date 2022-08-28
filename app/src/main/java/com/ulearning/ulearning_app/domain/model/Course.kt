package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Course(
    val id:Int,
    val name:String,
    val category:String
): Serializable
