package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Subscription(
    var amount: Int?= 0,
    var billing: Any?= null,
    var billing_id: Any?= null,
    var course: Course?= null,
    var course_id: Int?= 0,
    var deleted_at: Any?= null,
    var finished_at: Any?= null,
    var group: Group?= null,
    var group_id: Int?= 0,
    var has_certificate: Boolean?= false,
    var has_degree: Boolean?= false,
    var has_record: Boolean?= false,
    var id: Int?= 0,
    var is_finished: Boolean?= false,
    var last_connection_at: Any?= null,
    var next_payment_date: Any?= null,
    var purchased_certificate: Boolean?= false,
    var purchased_record: Boolean?= false,
    var rating: Any?= null,
    var registered_at: Any?= null,
    var registered_by: Int?= 0,
    var status: String?= "",
    var suspended_at: Any?= null,
    var suspended_by: Any?= null,
    var time_session: Int?= 0,
    var type: String?= "",
    var user: User?= null,
    var user_id: Int?= 0
)  : Serializable