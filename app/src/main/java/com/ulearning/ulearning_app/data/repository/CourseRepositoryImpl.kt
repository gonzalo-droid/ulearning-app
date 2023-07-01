package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.CourseMapper
import com.ulearning.ulearning_app.data.remote.entities.request.DownloadGuestFileRequest
import com.ulearning.ulearning_app.data.remote.entities.request.ShowGuestFileRequest
import com.ulearning.ulearning_app.data.remote.service.CourseService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.*
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl
@Inject constructor(
    private val service: CourseService,
    private val mapper: CourseMapper,
    private val dataStore: DataStoreConfig,
) : CourseRepository {
    override suspend fun getLearningPackage(learningPackageId: Int): Either<Failure, Subscription> {
        return when (
            val response = service.learningPackage(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                learningPackageId = learningPackageId,
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.subscriptionToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getSubscriptionsPackage(
        page: Int,
        type: String,
    ): Either<Failure, List<Subscription>> {
        return when (
            val response = service.subscriptionsPackage(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                perPage = 30,
                page = page,
                classification = type,
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listSubscriptionsToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getSubscriptions(
        page: Int,
        isFinished: Boolean,
    ): Either<Failure, List<Subscription>> {
        return when (
            val response = service.subscriptions(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                perPage = 1,
                page = page,
                isFinished = isFinished
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listSubscriptionsToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getCoursesTeacher(userId: Int): Either<Failure, List<Course>> {
        return when (
            val response = service.coursesTeacher(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", userId = userId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listCourseToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getCoursePercentage(courseIds: String): Either<Failure, List<CoursePercentage>> {
        return when (
            val response = service.coursePercentage(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", courseIds = courseIds
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listCoursePercentageToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun myFiles(subscriptionId: Int): Either<Failure, List<FileItem>> {
        return when (
            val response = service.myFiles(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", subscriptionId = subscriptionId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.myFilesToDomain(response.b))
            }

            is Either.Left -> { /*                val json = response.a.toString()
                Log.d("json list", json.encode())
                val convertedObject: JsonObject = Gson().fromJson(json, JsonObject::class.java)
                */
                Either.Left(response.a)
            }
        }
    }

    override suspend fun myCertificates(subscriptionId: Int): Either<Failure, FileItem> {
        return when (
            val response = service.myCertificates(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", subscriptionId = subscriptionId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.myFileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun myRecords(subscriptionId: Int): Either<Failure, FileItem> {
        return when (
            val response = service.myRecords(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", subscriptionId = subscriptionId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.myFileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun showGuestFile(name: String): Either<Failure, FileItem> {
        return when (
            val response = service.showGuestFile(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                request = ShowGuestFileRequest(name = name)
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.myFileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun checkAvailableFiles(subscriptionId: Int): Either<Failure, CheckAvailableFiles> {
        return when (
            val response = service.checkAvailableFiles(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", subscriptionId = subscriptionId
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.checkAvailableFilesToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun downloadFile(hash: String): Either<Failure, DownloadFile> {
        return when (
            val response = service.downloadFile(
                token = "${SettingRemote.BEARER} ${dataStore.token()}", hash = hash
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.downloadFileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun downloadGuestFile(
        hash: String,
        name: String,
    ): Either<Failure, DownloadFile> {
        return when (
            val response = service.downloadGuestFile(
                token = "${SettingRemote.BEARER} ${dataStore.token()}",
                request = DownloadGuestFileRequest(name = name, hash = hash)
            )
        ) {
            is Either.Right -> {
                Either.Right(mapper.downloadFileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }
}
