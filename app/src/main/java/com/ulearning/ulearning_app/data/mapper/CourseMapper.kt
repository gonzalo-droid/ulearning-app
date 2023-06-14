package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.CheckAvailableFilesResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CoursePackageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CoursePercentageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CourseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.DownloadFileResponse
import com.ulearning.ulearning_app.data.remote.entities.response.FileItemResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.domain.model.CheckAvailableFiles
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.model.Subscription

interface CourseMapper {
    suspend fun coursePackageToDomain(data: CoursePackageResponse): CoursePackage

    suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription>

    suspend fun listCourseToDomain(data: List<CourseResponse>): List<Course>

    suspend fun listCoursePercentageToDomain(data: List<CoursePercentageResponse>): List<CoursePercentage>

    suspend fun myFilesToDomain(data: List<FileItemResponse>): List<FileItem>

    suspend fun myFileToDomain(data: FileItemResponse): FileItem

    suspend fun checkAvailableFilesToDomain(data: CheckAvailableFilesResponse): CheckAvailableFiles

    suspend fun downloadFileToDomain(data: DownloadFileResponse): DownloadFile
}
