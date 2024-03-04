package com.ulearning.ulearning_app.data.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.ProfileMapper
import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.data.remote.service.ProfileService
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl
@Inject
constructor(
    private val service: ProfileService,
    private val mapper: ProfileMapper,
    private val dataStore: DataStoreConfig,
) : ProfileRepository {
    override suspend fun getPayment(page: Int): Either<Failure, List<Payment>> {
        return when (
            val response =
                service.payments(
                    token = "${SettingRemote.BEARER} ${dataStore.token()}",
                    perPage = 50,
                    page = 1,
                )
        ) {
            is Either.Right -> {
                Either.Right(mapper.listPaymentToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun updateProfile(data: ProfileRequest): Either<Failure, Profile> {
        return when (
            val response =
                service.profileUpdate(
                    token = "${SettingRemote.BEARER} ${dataStore.token()}",
                    body = data
                )
        ) {
            is Either.Right -> {
                Either.Right(mapper.profileToDomain(response.b))
            }

            is Either.Left -> Either.Left(response.a)
        }
    }
}
