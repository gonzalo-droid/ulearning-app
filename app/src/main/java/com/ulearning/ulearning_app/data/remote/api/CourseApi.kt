package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.DownloadGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.request.ShowGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*

interface CourseApi {

    @GET("subscriptions")
    suspend fun subscription(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("is_finished") isFinished: Boolean,
    ): Response<BaseResponse<List<SubscriptionResponse>>>

    @GET("subscriptions") // https://mockbin.org/bin/2afbba16-5d35-4018-849a-1d7436295976
    suspend fun subscriptionsPackage(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("classification") classification: String,
        @Query("includes") includes: String = "learning_package",
        // @Query("without_pagination") withoutPagination: Boolean = true,
    ): Response<BaseResponse<List<SubscriptionResponse>>>

    // http://ulearning-api.net/api/learning-packages/1/subscriptions
    // ?includes=learning_package,learning_package_items,course
    @GET("learning-packages/{learningPackageId}/subscriptions") // https://mockbin.org/bin/89417e80-4a8f-4ed4-8d47-ddf33f83e387
    suspend fun learningPackage(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("learningPackageId") learningPackageId: Int,
        @Query("includes") includes: String = "learning_package,learning_package_items,course",
    ): Response<BaseResponse<CoursePackageResponse>>

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

    @GET("my-files/{subscription_id}")
    suspend fun myFiles(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("subscription_id") subscriptionId: Int,
    ): Response<BaseResponse<List<FileItemResponse>>>

    @GET("check-available-files/{subscription_id}")
    suspend fun checkAvailableFiles(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("subscription_id") subscriptionId: Int,
    ): Response<BaseResponse<CheckAvailableFilesResponse>>

    @POST("my-certificates/{subscription_id}")
    suspend fun myCertificates(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("subscription_id") subscriptionId: Int,
    ): Response<BaseResponse<FileItemResponse>>

    @POST("show-guest-file")
    suspend fun showGuestFile(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body request: ShowGuestFileRequest,
    ): Response<BaseResponse<FileItemResponse>>

    @POST("my-records/{subscription_id}")
    suspend fun myRecords(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("subscription_id") subscriptionId: Int,
    ): Response<BaseResponse<FileItemResponse>>

    @GET("download-file/{hash}/hash")
    suspend fun downloadFile(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("hash") hash: String,
    ): Response<BaseResponse<DownloadFileResponse>>

    @POST("download-guest-file")
    suspend fun downloadGuestFile(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body request: DownloadGuestFileRequest,
    ): Response<BaseResponse<DownloadFileResponse>>
}
