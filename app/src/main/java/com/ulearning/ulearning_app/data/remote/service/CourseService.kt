package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.DownloadGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.request.ShowGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.response.*
import retrofit2.http.*

interface CourseService {
    suspend fun learningPackage(
        token: String,
        learningPackageId: Int,
    ): Either<Failure, SubscriptionResponse>

    suspend fun subscriptionsPackage(
        token: String,
        perPage: Int,
        page: Int,
        classification: String,
    ): Either<Failure, List<SubscriptionResponse>>

    suspend fun subscriptions(
        token: String,
        perPage: Int,
        page: Int,
        isFinished: Boolean,
    ): Either<Failure, List<SubscriptionResponse>>

    suspend fun coursesTeacher(
        token: String,
        userId: Int,
    ): Either<Failure, List<CourseResponse>>

    suspend fun coursePercentage(
        token: String,
        courseIds: String,
    ): Either<Failure, List<CoursePercentageResponse>>

    suspend fun myFiles(
        token: String,
        subscriptionId: Int,
    ): Either<Failure, List<FileItemResponse>>

    suspend fun myCertificates(
        token: String,
        subscriptionId: Int,
    ): Either<Failure, FileItemResponse>

    suspend fun myRecords(
        token: String,
        subscriptionId: Int,
    ): Either<Failure, FileItemResponse>

    suspend fun showGuestFile(
        token: String,
        request: ShowGuestFileRequest,
    ): Either<Failure, FileItemResponse>

    suspend fun checkAvailableFiles(
        token: String,
        subscriptionId: Int,
    ): Either<Failure, CheckAvailableFilesResponse>

    suspend fun downloadFile(
        token: String,
        hash: String,
    ): Either<Failure, DownloadFileResponse>

    suspend fun downloadGuestFile(
        token: String,
        request: DownloadGuestFileRequest,
    ): Either<Failure, DownloadFileResponse>
}
