package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class CoursePackageResponse(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("classification")
    val classification: String?,
    @SerializedName("course")
    val course: CourseResponse?,
    @SerializedName("course_id")
    val courseId: Any?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
    @SerializedName("finished_at")
    val finishedAt: Any?,
    @SerializedName("group")
    val group: GroupResponse?,
    @SerializedName("group_id")
    val groupId: Any?,
    @SerializedName("has_certificate")
    val hasCertificate: Boolean?,
    @SerializedName("has_degree")
    val hasDegree: Boolean?,
    @SerializedName("has_record")
    val hasRecord: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_finished")
    val isFinished: Boolean?,
    @SerializedName("last_connection_at")
    val lastConnectionAt: Any?,
    @SerializedName("learning_package")
    val learningPackage: LearningPackageResponse?,
    @SerializedName("learning_package_id")
    val learningPackageId: Int?,
    @SerializedName("purchased_certificate")
    val purchasedCertificate: Boolean?,
    @SerializedName("purchased_record")
    val purchasedRecord: Boolean?,
    @SerializedName("rating")
    val rating: Any?,
    @SerializedName("registered_at")
    val registeredAt: Any?,
    @SerializedName("registered_by")
    val registeredBy: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("suspended_at")
    val suspendedAt: Any?,
    @SerializedName("suspended_by")
    val suspendedBy: Any?,
    @SerializedName("suspended_reason")
    val suspendedReason: Any?,
    @SerializedName("time_session")
    val timeSession: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("user")
    val user: UserResponse?,
    @SerializedName("user_id")
    val userId: Int?
)
