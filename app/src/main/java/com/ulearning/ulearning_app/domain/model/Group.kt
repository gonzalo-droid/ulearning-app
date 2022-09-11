package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Group(
    var course_id: Int?= 0,
    var date_start: String?= "",
    var date_until: String?= "",
    var id: Int?= 0,
    var is_suspended: Boolean?= false,
    var is_unlimited: Boolean?= false,
    var members_count: Int?= 0,
    var name: String?= "",
    var students_count: Int?= 0,
    var teachers: List<Teacher>?= arrayListOf(),
    var teachers_count: Int?= 0,
    var vacancies: Int?= 0
) : Serializable