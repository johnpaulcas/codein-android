package com.johnpaulcas.watchly.persistence.datastore

/**
 * Created by johnpaulcas on 19/01/2021.
 */
interface AppDataStore {

    suspend fun saveString(key: String, value: String)

    suspend fun readString(key: String): String?

    suspend fun saveInt(key: String, value: Int)

    suspend fun readInt(key: String): Int?

}