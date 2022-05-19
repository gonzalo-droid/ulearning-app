package com.ulearning.ulearning_app.data.repository.courses

import com.ulearning.ulearning_app.data.api.ApiHelper
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun getCourses() = apiHelper.getCourses()
}