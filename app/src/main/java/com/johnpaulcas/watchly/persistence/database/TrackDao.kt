package com.johnpaulcas.watchly.persistence.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: Track)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tracks: List<Track>)

    @Query("SELECT * FROM track_table")
    fun getAllTracks(): LiveData<List<Track>>

    @Query("DELETE FROM track_table")
    suspend fun deleteAll()

}