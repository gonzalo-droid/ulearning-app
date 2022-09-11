package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Subscription(
    var amount: Int? = 0,
    var course: Course? = null,
    var course_id: Int? = 0,
    var group: Group? = null,
    var group_id: Int? = 0,
    var has_certificate: Boolean? = false,
    var has_degree: Boolean? = false,
    var has_record: Boolean? = false,
    var id: Int? = 0,
    var is_finished: Boolean? = false,
    var purchased_certificate: Boolean? = false,
    var purchased_record: Boolean? = false,
    var status: String? = "",
    var time_session: Int? = 0,
    var type: String? = "",
    var user: User? = null,
    var user_id: Int? = 0
) : Serializable