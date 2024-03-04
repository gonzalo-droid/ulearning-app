package com.ulearning.ulearning_app.data.remote.service

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.api.ProfileApi
import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.data.remote.entities.response.PaymentResponse
import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileServiceImpl
@Inject
constructor(
    private val api: ProfileApi,
    private val networkHandler: NetworkHandler,
) : ProfileService {
    override suspend fun payments(
        token: String,
        perPage: Int,
        page: Int,
    ): Either<Failure, List<PaymentResponse>> {
        return networkHandler.callServiceBaseList {
            api.payments(token = token, perPage = perPage, page = page)
        }
    }

    override suspend fun profileUpdate(
        token: String,
        body: ProfileRequest
    ): Either<Failure, ProfileResponse> {
        return networkHandler.callServiceBase {
            api.profileUpdate(token = token, body = body)
        }
    }
}
