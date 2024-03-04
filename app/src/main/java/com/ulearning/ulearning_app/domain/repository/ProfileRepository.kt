package com.ulearning.ulearning_app.domain.repository

import com.ulearning.ulearning_app.core.functional.Either
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.Profile

interface ProfileRepository {
    suspend fun getPayment(page: Int): Either<Failure, List<Payment>>

    suspend fun updateProfile(data: ProfileRequest): Either<Failure, Profile>
}
