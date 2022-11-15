package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.domain.model.*


interface CourseMapper {


    suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription>

    suspend fun listCourseToDomain(data: List<CourseResponse>): List<Course>

    suspend fun listCoursePercentageToDomain(data: List<CoursePercentageResponse>): List<CoursePercentage>

    suspend fun myFilesToDomain(data: List<FileItemResponse>): List<FileItem>

    suspend fun checkAvailableFilesToDomain(data: CheckAvailableFilesResponse): CheckAvailableFiles

    suspend fun downloadFileToDomain(data: DownloadFileResponse): DownloadFile

}