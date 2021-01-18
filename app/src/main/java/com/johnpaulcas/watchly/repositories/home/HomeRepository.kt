package com.johnpaulcas.watchly.repositories.home

import com.johnpaulcas.watchly.api.reponse.TrackResponse
import retrofit2.Response

/**
 * Created by johnpaulcas on 18/01/2021.
 */
interface HomeRepository {

    /**
     * Get the list of track
     */
    suspend fun getTracks(): Response<TrackResponse>

}