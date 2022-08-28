package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.AuthApi
import com.ulearning.ulearning_app.data.remote.api.CourseApi
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SubscriptionRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseServiceImpl
    @Inject constructor(
        private val courseApi: CourseApi,
        private val networkHandler: NetworkHandler
    ) : CourseService {

    override suspend fun subscriptions(token :String, body: SubscriptionRequest): Either<Failure, List<SubscriptionResponse>> {
        return networkHandler.callServiceBase { courseApi.subscription(token, body) }
    }

}