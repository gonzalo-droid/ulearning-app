package com.ulearning.ulearning_app.data.repository.courses

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.CourseMapper
import com.ulearning.ulearning_app.data.remote.entities.request.SubscriptionRequest
import com.ulearning.ulearning_app.data.remote.service.CourseService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl
@Inject constructor(
    private val service: CourseService,
    private val mapper: CourseMapper,
    private val dataStore: DataStoreConfig
) : CourseRepository {

    override suspend fun getSubscriptions(
        page: Int,
        isFinished: Boolean
    ): Either<Failure, List<Subscription>> {
        return when (val response = service.subscriptions(
            token = "${SettingRemote.BEARER} ${dataStore.token()}",
            perPage = 20,
            page = page,
            isFinished = isFinished
        )) {
            is Either.Right -> {
                Either.Right(mapper.listSubscriptionToDomain(response.b))
            }
            is Either.Left -> Either.Left(response.a)
        }
    }
}