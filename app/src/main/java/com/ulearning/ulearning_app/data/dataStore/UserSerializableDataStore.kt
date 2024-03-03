package com.ulearning.ulearning_app.data.dataStore

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.ulearning.ulearning_app.UserPreference
import java.io.InputStream
import java.io.OutputStream

object UserSerializableDataStore : Serializer<UserPreference> {

    override val defaultValue: UserPreference
        get() = UserPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreference {

        try {

            return UserPreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {

            throw exception
        }
    }

    override suspend fun writeTo(t: UserPreference, output: OutputStream) = t.writeTo(output)
}
