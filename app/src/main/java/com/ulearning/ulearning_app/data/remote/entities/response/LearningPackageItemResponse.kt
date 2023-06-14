package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class LearningPackageItemResponse(
    @SerializedName("course") val course: CourseResponse?,
    @SerializedName("course_id") val courseId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_required") val isRequired: Boolean?,
    @SerializedName("learning_package_id") val learningPackageId: Int?,
)
