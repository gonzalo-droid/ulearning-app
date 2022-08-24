package com.ulearning.ulearning_app.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.data.dataStore.UserSerializableDataStore
import com.ulearning.ulearning_app.data.dataStore.config.DataStoreConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.com.pex.UserPreference


@Module
@InstallIn(SingletonComponent::class)
object DataStore {

    private val Context.dataStore: DataStore<UserPreference> by dataStore(
        fileName = Config.NAME_DATA_STORE,
        serializer = UserSerializableDataStore
    )

    @Provides
    @Reusable
    fun provideProtoDataStore(@ApplicationContext context: Context) =
        context.dataStore

    @Provides
    @Reusable
    internal fun providesDataStoreConfig(
        @ApplicationContext context: Context,
        dataStore: DataStore<UserPreference>
    ) = DataStoreConfig(context, dataStore)
}