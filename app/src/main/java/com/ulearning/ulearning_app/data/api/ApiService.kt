package com.ulearning.ulearning_app.data.api

import com.ulearning.ulearning_app.domain.model.Course
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("courses")
    fun getCourses(): Response<Course>

}