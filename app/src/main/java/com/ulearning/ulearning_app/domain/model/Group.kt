package com.ulearning.ulearning_app.domain.model

import java.io.Serializable

data class Group(
    var courseId: Int?= 0,
    var dateStart: String?= "",
    var dateUntil: String?= "",
    var id: Int?= 0,
    var isSuspended: Boolean?= false,
    var isUnlimited: Boolean?= false,
    var membersCount: Int?= 0,
    var name: String?= "",
    var studentsCount: Int?= 0,
    var teachers: List<Teacher>?= arrayListOf(),
    var teachersCount: Int?= 0,
    var vacancies: Int?= 0
) : Serializable