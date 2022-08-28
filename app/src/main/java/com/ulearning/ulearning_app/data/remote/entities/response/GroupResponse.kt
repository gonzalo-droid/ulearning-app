package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("course")
    var course: Any = Any(),
    @SerializedName("course_id")
    var courseId: Int = 0,
    @SerializedName("date_start")
    var dateStart: String = "",
    @SerializedName("date_until")
    var dateUntil: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("is_suspended")
    var isSuspended: Boolean = false,
    @SerializedName("is_unlimited")
    var isUnlimited: Boolean = false,
    @SerializedName("members_count")
    var membersCount: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("schedules")
    var schedules: Any = Any(),
    @SerializedName("students_count")
    var studentsCount: Int = 0,
    @SerializedName("suspended_at")
    var suspendedAt: Any = Any(),
    @SerializedName("teachers")
    var teachers: Any = Any(),
    @SerializedName("teachers_count")
    var teachersCount: Int = 0,
    @SerializedName("vacancies")
    var vacancies: Int = 0
)