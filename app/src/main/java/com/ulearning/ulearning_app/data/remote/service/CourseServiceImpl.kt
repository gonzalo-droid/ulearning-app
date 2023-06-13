package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.CourseApi
import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.DownloadGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.request.ShowGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseServiceImpl
@Inject constructor(
    private val courseApi: CourseApi, private val networkHandler: NetworkHandler
) : CourseService {
    override suspend fun subscriptionsPackage(
        token: String,
        perPage: Int,
        page: Int,
        includes: String,
    ): Either<Failure, List<SubscriptionResponse>> {
        return networkHandler.callServiceBaseList {
            courseApi.subscriptionsPackage(
                token, perPage, page, includes
            )
        }
    }

    override suspend fun subscriptions(
        token: String,
        perPage: Int,
        page: Int,
        isFinished: Boolean,
    ): Either<Failure, List<SubscriptionResponse>> {
        return networkHandler.callServiceBaseList {
            courseApi.subscription(
                token, perPage, page, isFinished
            )
        }
    }

    override suspend fun coursesTeacher(
        token: String, userId: Int
    ): Either<Failure, List<CourseResponse>> {
        return networkHandler.callServiceBaseList {
            courseApi.coursesTeacher(
                token, userId
            )
        }
    }

    override suspend fun coursePercentage(
        token: String,
        courseIds: String
    ): Either<Failure, List<CoursePercentageResponse>> {
        return networkHandler.callServiceBaseList {
            courseApi.coursePercentage(
                token, courseIds
            )
        }
    }

    override suspend fun myFiles(
        token: String,
        subscriptionId: Int
    ): Either<Failure, List<FileItemResponse>> {
        return networkHandler.callServiceBaseList {
            courseApi.myFiles(
                token, subscriptionId
            )
        }
    }

    override suspend fun myCertificates(
        token: String,
        subscriptionId: Int
    ): Either<Failure, FileItemResponse> {
        return networkHandler.callServiceBase {
            courseApi.myCertificates(
                token, subscriptionId
            )
        }
    }

    override suspend fun myRecords(
        token: String,
        subscriptionId: Int
    ): Either<Failure, FileItemResponse> {
        return networkHandler.callServiceBase {
            courseApi.myRecords(
                token, subscriptionId
            )
        }
    }

    override suspend fun showGuestFile(
        token: String,
        request: ShowGuestFileRequest
    ): Either<Failure, FileItemResponse> {
        return networkHandler.callServiceBase {
            courseApi.showGuestFile(
                token, request
            )
        }
    }

    override suspend fun checkAvailableFiles(
        token: String,
        subscriptionId: Int
    ): Either<Failure, CheckAvailableFilesResponse> {
        return networkHandler.callServiceBase {
            courseApi.checkAvailableFiles(
                token, subscriptionId
            )
        }
    }

    override suspend fun downloadFile(
        token: String,
        hash: String
    ): Either<Failure, DownloadFileResponse> {
        return networkHandler.callServiceBase {
            courseApi.downloadFile(
                token, hash
            )
        }
    }

    override suspend fun downloadGuestFile(
        token: String,
        request: DownloadGuestFileRequest
    ): Either<Failure, DownloadFileResponse> {
        return networkHandler.callServiceBase {
            courseApi.downloadGuestFile(
                token, request
            )
        }
    }
}