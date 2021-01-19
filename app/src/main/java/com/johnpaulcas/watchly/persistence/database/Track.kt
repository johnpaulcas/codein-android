package com.johnpaulcas.watchly.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track_table")
data class Track(
    @PrimaryKey
    val trackId: Int,
    val artworkUrl100: String,
    val collectionId: Int,
    val collectionName: String,
    val trackName: String,
    val primaryGenreName: String,
    val trackPrice: Double,
    val longDescription: String
)