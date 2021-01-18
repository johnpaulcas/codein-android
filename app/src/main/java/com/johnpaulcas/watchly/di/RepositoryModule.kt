package com.johnpaulcas.watchly.di

import com.johnpaulcas.watchly.api.ApiHelper
import com.johnpaulcas.watchly.repositories.home.HomeRepository
import com.johnpaulcas.watchly.repositories.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(apiHelper: ApiHelper): HomeRepository {
        return HomeRepositoryImpl(apiHelper)
    }

}