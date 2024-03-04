package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class CoursePercentage(
    var percentage: String,
    var courseId: Int,
) : Serializable
