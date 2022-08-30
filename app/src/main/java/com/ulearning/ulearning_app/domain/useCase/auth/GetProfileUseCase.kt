package com.ulearning.ulearning_app.domain.useCase.auth

import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetProfileUseCase
@Inject constructor(private val authRepository: AuthRepository) :
    BaseUseCase<Profile, BaseUseCase.None>() {

    override suspend fun run(params: None) = authRepository.profile()

}