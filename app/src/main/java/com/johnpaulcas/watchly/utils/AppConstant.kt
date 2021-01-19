package com.johnpaulcas.watchly.utils

import androidx.room.ColumnInfo

/**
 * Created by johnpaulcas on 18/01/2021.
 */
object AppConstant {

    // api base url
    const val BASE_URL = "https://itunes.apple.com"

    // api params value
    const val TERM = "star"
    const val COUNTRY = "au"
    const val MEDIA = "movie"

    object Genre {
        const val SCI_FI = "Sci-Fi & Fantasy"
        const val ACTION_AND_ADVENTURE = "Action & Adventure"
        const val COMEDY = "Comedy"
        const val ROMANCE = "Romance"
        const val DRAMA = "Drama"
        const val KINDS_AND_FAMILY = "Kids & Family"
        const val DOCUMENTARY = "Documentary"
        const val ANIME = "Anime"
        const val WESTERN = "Western"
    }


    object DataStore {
        const val DATE_VISITED = "date_visited"
        const val LAST_KNOWN_SCREEN = "last_know_screen"
        const val CACHED_DATA = "cached_data"
    }

    object Screen {
        const val HOME = "Watchly"
        const val TRACK_DETAILS = "Track Details"
    }
}