package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SubscriptionRequest
import com.ulearning.ulearning_app.data.remote.entities.response.CoursePercentageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CourseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CourseApi {

    @GET("subscriptions")
    suspend fun subscription(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("is_finished") isFinished: Boolean,
    ): Response<BaseResponse<List<SubscriptionResponse>>>

    @GET("courses")
    suspend fun coursesTeacher(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("user_id") userId: Int,
    ): Response<BaseResponse<List<CourseResponse>>>

    @GET("courses-advances")
    suspend fun coursePercentage(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("course_ids") courseIds: String,
    ): Response<BaseResponse<List<CoursePercentageResponse>>>

}
