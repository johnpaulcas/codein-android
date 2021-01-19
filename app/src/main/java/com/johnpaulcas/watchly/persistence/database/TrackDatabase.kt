package com.johnpaulcas.watchly.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
/**
 * Created by johnpaulcas on 19/01/2021.
 */
@Database(
    entities = [Track::class],
    version = 1
)
abstract class TrackDatabase: RoomDatabase() {

    abstract fun trackDao(): TrackDao

}