package com.ulearning.ulearning_app.core.di

import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import com.ulearning.ulearning_app.data.mapper.*
import com.ulearning.ulearning_app.data.remote.service.AuthService
import com.ulearning.ulearning_app.data.remote.service.CourseService
import com.ulearning.ulearning_app.data.remote.service.ConversationService
import com.ulearning.ulearning_app.data.remote.service.TopicService
import com.ulearning.ulearning_app.data.repository.AuthRepositoryImpl
import com.ulearning.ulearning_app.data.repository.CourseRepositoryImpl
import com.ulearning.ulearning_app.data.repository.ConversationRepositoryImpl
import com.ulearning.ulearning_app.data.repository.TopicRepositoryImpl
import com.ulearning.ulearning_app.domain.repository.AuthRepository
import com.ulearning.ulearning_app.domain.repository.CourseRepository
import com.ulearning.ulearning_app.domain.repository.ConversationRepository
import com.ulearning.ulearning_app.domain.repository.TopicRepository
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
    fun provideConversationMapper(): ConversationMapper = ConversationMapperImpl()

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

    @Provides
    @Singleton
    fun provideConversationRepository(
        service: ConversationService,
        mapper: ConversationMapper,
        dataStore: DataStoreConfig
    ): ConversationRepository = ConversationRepositoryImpl(service, mapper, dataStore)
}