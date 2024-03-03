package com.ulearning.ulearning_app.core.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.ulearning.ulearning_app.BuildConfig
import com.ulearning.ulearning_app.data.remote.api.AuthApi
import com.ulearning.ulearning_app.data.remote.api.ConversationApi
import com.ulearning.ulearning_app.data.remote.api.CourseApi
import com.ulearning.ulearning_app.data.remote.api.TopicApi
import com.ulearning.ulearning_app.data.remote.network.NetworkHandler
import com.ulearning.ulearning_app.data.remote.service.*
import com.ulearning.ulearning_app.data.remote.utils.ConnectionUtils
import com.ulearning.ulearning_app.data.remote.utils.ConnectionUtilsImpl
import com.ulearning.ulearning_app.data.remote.utils.SupportInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Suppress("PrivatePropertyName")
    private val TIMEOUT: Long = 10

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        providesGsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(providesGsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).serializeNulls()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: SupportInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(interceptor).addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().build())
            }
            .addInterceptor(authInterceptor)

        if (BuildConfig.DEBUG) { client.addInterceptor(OkHttpProfilerInterceptor()) }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideConnection(@ApplicationContext appContext: Context): ConnectionUtils =
        ConnectionUtilsImpl(appContext)

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideCourseApi(
        retrofit: Retrofit
    ): CourseApi = retrofit.create(CourseApi::class.java)

    @Provides
    @Singleton
    fun provideTopicApi(
        retrofit: Retrofit
    ): TopicApi = retrofit.create(TopicApi::class.java)

    @Provides
    @Singleton
    fun provideConversationApi(
        retrofit: Retrofit
    ): ConversationApi = retrofit.create(ConversationApi::class.java)

    @Provides
    @Singleton
    fun provideAuthService(
        authApi: AuthApi,
        networkHandler: NetworkHandler
    ): AuthService = AuthServiceImpl(authApi, networkHandler)

    @Provides
    @Singleton
    fun provideCourseService(
        authApi: CourseApi,
        networkHandler: NetworkHandler
    ): CourseService = CourseServiceImpl(authApi, networkHandler)

    @Provides
    @Singleton
    fun provideTopicService(
        authApi: TopicApi,
        networkHandler: NetworkHandler
    ): TopicService = TopicServiceImpl(authApi, networkHandler)

    @Provides
    @Singleton
    fun provideConversationService(
        authApi: ConversationApi,
        networkHandler: NetworkHandler
    ): ConversationService = ConversationServiceImpl(authApi, networkHandler)
}
