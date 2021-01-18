package com.johnpaulcas.watchly.api

import com.johnpaulcas.watchly.api.reponse.TrackResponse
import retrofit2.Response

/**
 * Created by johnpaulcas on 18/01/2021.
 */
interface ApiHelper {
    suspend fun getTracks(): Response<TrackResponse>
}