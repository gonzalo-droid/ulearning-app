package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.ProfileResponse
import com.ulearning.ulearning_app.domain.model.Profile
import javax.inject.Singleton

@Singleton
class AuthMapperImpl : AuthMapper {
    override suspend fun profileToDomain(data: ProfileResponse): Profile {
        return data.let {
            Profile(
                address = it.address,
                avatar = it.avatar,
                country = it.country,
                countryId = it.countryId,
                dateOfBirth = it.dateOfBirth,
                documentNumber = it.documentNumber,
                documentType = it.documentType,
                email = it.email,
                firstName = it.firstName,
                gender = it.gender,
                id = it.id,
                lastName = it.lastName,
                name = it.name,
                permissions = it.permissions,
                phone = it.phone,
                phoneCode = it.phoneCode,
                plan = it.plan,
                role = it.role,
                secondLastName = it.secondLastName,
                suspendedAt = it.suspendedAt,
            )
        }
    }


}