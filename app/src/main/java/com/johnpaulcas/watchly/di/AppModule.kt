package com.johnpaulcas.watchly.di

import androidx.viewbinding.BuildConfig
import com.johnpaulcas.watchly.api.ApiHelper
import com.johnpaulcas.watchly.api.ApiHelperImpl
import com.johnpaulcas.watchly.api.ApiService
import com.johnpaulcas.watchly.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = AppConstant.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}