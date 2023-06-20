package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Subscription(
    var amount: Int? = 0,
    var course: Course? = null,
    var courseId: Int,
    var group: Group? = null,
    var groupId: Int? = 0,
    var hasCertificate: Boolean? = false,
    var hasDegree: Boolean? = false,
    var hasRecord: Boolean? = false,
    var id: Int? = 0,
    var isFinished: Boolean? = false,
    var purchasedCertificate: Boolean? = false,
    var purchasedRecord: Boolean? = false,
    var status: String? = "",
    var timeSession: Int? = 0,
    var type: String? = "",
    var user: User? = null,
    var userId: Int? = 0,
    var learningPackage: LearningPackage? = null,
    val learningPackageId: Int? = 0,
) : Serializable
