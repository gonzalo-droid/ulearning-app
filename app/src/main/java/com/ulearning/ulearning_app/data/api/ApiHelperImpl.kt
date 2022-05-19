package com.ulearning.ulearning_app.data.api

import com.ulearning.ulearning_app.domain.model.Course
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private  val apiService: ApiService
) : ApiHelper {
    override fun getCourses(): Response<Course> = apiService.getCourses()

}