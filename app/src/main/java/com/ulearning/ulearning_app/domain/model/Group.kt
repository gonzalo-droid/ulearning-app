package com.ulearning.ulearning_app.domain.model

data class Group(
    var course: Any = Any(),
    var course_id: Int = 0,
    var date_start: String = "",
    var date_until: String = "",
    var id: Int = 0,
    var is_suspended: Boolean = false,
    var is_unlimited: Boolean = false,
    var members_count: Int = 0,
    var name: String = "",
    var schedules: Any = Any(),
    var students_count: Int = 0,
    var suspended_at: Any = Any(),
    var teachers: Any = Any(),
    var teachers_count: Int = 0,
    var vacancies: Int = 0
)