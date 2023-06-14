package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class CoursePercentageResponse(
    @SerializedName("percentage")
    var percentage: String,
    @SerializedName("course_id")
    var courseId: Int,
)
