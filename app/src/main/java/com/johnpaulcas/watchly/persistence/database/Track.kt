package com.johnpaulcas.watchly.persistence.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "track_table")
@Parcelize
data class Track(
    @PrimaryKey
    val trackId: Int,

    @ColumnInfo(name = "artwork_url")
    val artworkUrl100: String,

    @ColumnInfo(name = "track_name")
    val trackName: String,

    @ColumnInfo(name = "genre")
    val primaryGenreName: String,

    @ColumnInfo(name = "price")
    val trackPrice: Double,

    @ColumnInfo(name = "description")
    val longDescription: String
): Parcelable