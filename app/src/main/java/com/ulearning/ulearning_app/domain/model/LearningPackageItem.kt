package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class LearningPackageItem(
    val course: Course?,
    val courseId: Int? = 0,
    val id: Int? = 0,
    val isRequired: Boolean? = false,
    val learningPackageId: Int? = 0,
) : Serializable
