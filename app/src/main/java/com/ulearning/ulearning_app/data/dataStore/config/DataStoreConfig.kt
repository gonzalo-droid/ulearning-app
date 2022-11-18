package com.ulearning.ulearning_app.data.dataStore.config

import android.content.Context
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.ulearning.ulearning_app.UserPreference

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

    suspend fun saveRole(role: String) {

        dataStore.updateData { update ->

            update.toBuilder().setRole(role).build()
        }
    }

    suspend fun saveId(id: Int) {

        dataStore.updateData { update ->

            update.toBuilder().setId(id).build()
        }
    }


    fun token(): String = runBlocking { dataStore.data.first().token }

    fun userName(): String = runBlocking { dataStore.data.first().username }

    fun role(): String = runBlocking { dataStore.data.first().role }

    fun id(): Int = runBlocking { dataStore.data.first().id }

}