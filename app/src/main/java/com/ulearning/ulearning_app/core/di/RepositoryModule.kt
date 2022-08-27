package com.ulearning.ulearning_app.core.di

import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.AuthMapper
import com.ulearning.ulearning_app.data.mapper.AuthMapperImpl
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.repository.auth.AuthRepositoryImpl
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthMapper(): AuthMapper = AuthMapperImpl()


    @Provides
    @Singleton
    fun provideAuthRepository(
        service: AuthService,
        dataStore: DataStoreConfig
    ): AuthRepository = AuthRepositoryImpl(service, dataStore)

}