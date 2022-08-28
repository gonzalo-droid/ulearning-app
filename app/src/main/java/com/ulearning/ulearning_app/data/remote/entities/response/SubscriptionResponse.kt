package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class SubscriptionResponse(
    @SerializedName("amount")
    var amount: Int = 0,
    @SerializedName("billing")
    var billing: Any = Any(),
    @SerializedName("billing_id")
    var billingId: Any = Any(),
    @SerializedName("course")
    var course: CourseResponse = CourseResponse(),
    @SerializedName("course_id")
    var courseId: Int = 0,
    @SerializedName("deleted_at")
    var deletedAt: Any = Any(),
    @SerializedName("finished_at")
    var finishedAt: Any = Any(),
    @SerializedName("group")
    var group: GroupResponse = GroupResponse(),
    @SerializedName("group_id")
    var groupId: Int = 0,
    @SerializedName("has_certificate")
    var hasCertificate: Boolean = false,
    @SerializedName("has_degree")
    var hasDegree: Boolean = false,
    @SerializedName("has_record")
    var hasRecord: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("is_finished")
    var isFinished: Boolean = false,
    @SerializedName("last_connection_at")
    var lastConnectionAt: Any = Any(),
    @SerializedName("next_payment_date")
    var nextPaymentDate: Any = Any(),
    @SerializedName("purchased_certificate")
    var purchasedCertificate: Boolean = false,
    @SerializedName("purchased_record")
    var purchasedRecord: Boolean = false,
    @SerializedName("rating")
    var rating: Any = Any(),
    @SerializedName("registered_at")
    var registeredAt: Any = Any(),
    @SerializedName("registered_by")
    var registeredBy: Int = 0,
    @SerializedName("status")
    var status: String = "",
    @SerializedName("suspended_at")
    var suspendedAt: Any = Any(),
    @SerializedName("suspended_by")
    var suspendedBy: Any = Any(),
    @SerializedName("time_session")
    var timeSession: Int = 0,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("user")
    var userResponse: UserResponse = UserResponse(),
    @SerializedName("user_id")
    var userId: Int = 0
)