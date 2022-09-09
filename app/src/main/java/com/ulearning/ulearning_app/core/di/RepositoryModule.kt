package com.ulearning.ulearning_app.core.di

import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.*
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.remote.service.CourseService
import com.ulearning.ulearning_app.data.remote.service.TopicService
import com.ulearning.ulearning_app.data.repository.auth.AuthRepositoryImpl
import com.ulearning.ulearning_app.data.repository.courses.CourseRepositoryImpl
import com.ulearning.ulearning_app.data.repository.topic.TopicRepositoryImpl
import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.repository.courses.CourseRepository
import com.ulearning.ulearning_app.domain.repository.topic.TopicRepository
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
    fun provideTopicMapper(): TopicMapper = TopicMapperImpl()

    @Provides
    @Singleton
    fun provideAuthRepository(
        service: AuthService,
        mapper: AuthMapper,
        dataStore: DataStoreConfig
    ): AuthRepository = AuthRepositoryImpl(service, mapper, dataStore)

    @Provides
    @Singleton
    fun provideCourseRepository(
        service: CourseService,
        mapper: CourseMapper,
        dataStore: DataStoreConfig
    ): CourseRepository = CourseRepositoryImpl(service, mapper, dataStore)

    @Provides
    @Singleton
    fun provideTopicRepository(
        service: TopicService,
        mapper: TopicMapper,
        dataStore: DataStoreConfig
    ): TopicRepository = TopicRepositoryImpl(service, mapper, dataStore)
}