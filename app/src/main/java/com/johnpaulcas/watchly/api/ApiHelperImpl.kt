package com.johnpaulcas.watchly.api

import com.johnpaulcas.watchly.api.reponse.TrackResponse
import com.johnpaulcas.watchly.utils.AppConstant
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by johnpaulcas on 18/01/2021.
 */
class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {

    override suspend fun getTracks(): Response<TrackResponse> =
        apiService.getTracks(AppConstant.TERM, AppConstant.COUNTRY, AppConstant.MEDIA)

}