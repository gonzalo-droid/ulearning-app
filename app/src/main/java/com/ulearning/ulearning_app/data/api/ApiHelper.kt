package com.ulearning.ulearning_app.data.api

import com.ulearning.ulearning_app.domain.model.Course
import retrofit2.Response

interface ApiHelper {

    fun getCourses():Response<Course>
}