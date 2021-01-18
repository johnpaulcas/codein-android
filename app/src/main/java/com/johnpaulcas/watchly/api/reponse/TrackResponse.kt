package com.johnpaulcas.watchly.api.reponse

import com.johnpaulcas.watchly.database.model.TrackModel

data class TrackResponse(
    val resultCount: Int,
    val results: List<TrackModel>
)