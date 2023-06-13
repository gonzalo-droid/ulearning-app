package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.response.CheckAvailableFilesResponse
import com.ulearning.ulearning_app.data.remote.entities.response.DownloadFileResponse
import com.ulearning.ulearning_app.data.remote.entities.response.FileItemResponse
import com.ulearning.ulearning_app.domain.model.*

interface CourseRepository {

    suspend fun getSubscriptionsPackage(
        page: Int,
        type: String,
    ): Either<Failure, List<Subscription>>

    suspend fun getSubscriptions(
        page: Int,
        isFinished: Boolean,
    ): Either<Failure, List<Subscription>>

    suspend fun getCoursesTeacher(
        userId: Int,
    ): Either<Failure, List<Course>>

    suspend fun getCoursePercentage(
        courseIds: String,
    ): Either<Failure, List<CoursePercentage>>

    suspend fun myFiles(
        subscriptionId: Int,
    ): Either<Failure, List<FileItem>>

    suspend fun myCertificates(
        subscriptionId: Int,
    ): Either<Failure, FileItem>

    suspend fun myRecords(
        subscriptionId: Int,
    ): Either<Failure, FileItem>

    suspend fun showGuestFile(
        name: String,
    ): Either<Failure, FileItem>

    suspend fun checkAvailableFiles(
        subscriptionId: Int,
    ): Either<Failure, CheckAvailableFiles>

    suspend fun downloadFile(
        hash: String,
    ): Either<Failure, DownloadFile>

    suspend fun downloadGuestFile(
        hash: String,
        name: String,
    ): Either<Failure, DownloadFile>

}
