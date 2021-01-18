package com.johnpaulcas.watchly.repositories.home

import com.johnpaulcas.watchly.api.ApiHelper
import javax.inject.Inject

/**
 * Created by johnpaulcas on 18/01/2021.
 */
class HomeRepositoryImpl constructor (
    private val apiHelper: ApiHelper
): HomeRepository {

    override suspend fun getTracks() = apiHelper.getTracks()


}