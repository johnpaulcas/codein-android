package com.johnpaulcas.watchly.persistence.datastore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first

/**
 * Created by johnpaulcas on 19/01/2021.
 */
class AppDataStoreImpl(private val datastore: DataStore<Preferences>): AppDataStore {

    override suspend fun saveString(key: String, value: String) {
        datastore.edit { settings ->
            settings[preferencesKey<String>(key)] = value
        }
    }

    override suspend fun readString(
        key: String
    ): String? = datastore.data.first()[preferencesKey<String>(key)]

    override suspend fun saveInt(key: String, value: Int) {
        datastore.edit { settings ->
            settings[preferencesKey<Int>(key)] = value
        }
    }

    override suspend fun readInt(
        key: String
    ): Int? = datastore.data.first()[preferencesKey<Int>(key)]

}