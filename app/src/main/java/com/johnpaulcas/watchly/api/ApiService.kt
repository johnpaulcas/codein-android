package com.johnpaulcas.watchly.api

import com.johnpaulcas.watchly.api.reponse.TrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by johnpaulcas on 18/01/2021.
 */
interface ApiService {

//    @GET("/search?term=star&amp;country=au&amp;media=movie")
    @GET("/search")
    suspend fun getTracks(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Response<TrackResponse>

}