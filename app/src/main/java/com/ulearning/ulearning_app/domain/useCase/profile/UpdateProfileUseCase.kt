package com.ulearning.ulearning_app.domain.useCase.profile

import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.domain.model.Payment
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.repository.ProfileRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class UpdateProfileUseCase
@Inject constructor(private val repository: ProfileRepository) :
    BaseUseCase<Profile, UpdateProfileUseCase.Params>() {
    override suspend fun run(params: Params) = repository.updateProfile(params.data)

    data class Params(val data: ProfileRequest)
}
