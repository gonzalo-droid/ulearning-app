package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.SubscriptionRequest
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse

interface CourseService {

    suspend fun subscriptions(token : String, body: SubscriptionRequest): Either<Failure, List<SubscriptionResponse>>

}