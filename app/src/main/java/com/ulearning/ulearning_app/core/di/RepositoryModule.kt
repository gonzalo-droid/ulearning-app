package com.ulearning.ulearning_app.core.di

import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.AuthMapper
import com.ulearning.ulearning_app.data.mapper.AuthMapperImpl
import com.ulearning.ulearning_app.data.mapper.CourseMapper
import com.ulearning.ulearning_app.data.mapper.CourseMapperImpl
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.remote.service.CourseService
import com.ulearning.ulearning_app.data.repository.auth.AuthRepositoryImpl
import com.ulearning.ulearning_app.data.repository.courses.CourseRepositoryImpl
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
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
    fun provideCourseMapper(): CourseMapper = CourseMapperImpl()


    @Provides
    @Singleton
    fun provideAuthMapper(): AuthMapper = AuthMapperImpl()


    @Provides
    @Singleton
    fun provideAuthRepository(
        service: AuthService,
        dataStore: DataStoreConfig
    ): AuthRepository = AuthRepositoryImpl(service, dataStore)

    @Provides
    @Singleton
    fun provideCourseRepository(
        service: CourseService,
        mapper: CourseMapper,
        dataStore: DataStoreConfig
    ): CourseRepository = CourseRepositoryImpl(service, mapper, dataStore)
}