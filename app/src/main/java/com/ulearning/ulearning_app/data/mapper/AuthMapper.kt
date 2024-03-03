package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.domain.model.Profile

interface AuthMapper {

    suspend fun profileToDomain(data: ProfileResponse): Profile
}
