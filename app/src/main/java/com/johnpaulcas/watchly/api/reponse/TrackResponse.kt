package com.johnpaulcas.watchly.api.reponse

import com.johnpaulcas.watchly.persistence.database.Track

data class TrackResponse(
    val resultCount: Int,
    val results: List<Track>
)