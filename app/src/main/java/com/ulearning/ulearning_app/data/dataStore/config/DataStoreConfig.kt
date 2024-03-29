package com.ulearning.ulearning_app.data.dataStore.config

import android.content.Context
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pe.com.pex.UserPreference
import javax.inject.Inject

class DataStoreConfig @Inject constructor(
        private val context: Context,
        private val dataStore: DataStore<UserPreference>
    ){

    suspend fun saveToken(token: String) {

        dataStore.updateData { update ->

            update.toBuilder().setToken(token).build()
        }
    }

    suspend fun saveUserName(userName: String) {

        dataStore.updateData { update ->

            update.toBuilder().setUsername(userName).build()
        }
    }




    fun token(): String = runBlocking { dataStore.data.first().token }

    fun userName(): String = runBlocking { dataStore.data.first().username }

}