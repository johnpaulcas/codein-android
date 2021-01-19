package com.johnpaulcas.watchly.di

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.johnpaulcas.watchly.BuildConfig
import com.johnpaulcas.watchly.api.ApiHelper
import com.johnpaulcas.watchly.api.ApiHelperImpl
import com.johnpaulcas.watchly.api.ApiService
import com.johnpaulcas.watchly.persistence.database.TrackDatabase
import com.johnpaulcas.watchly.persistence.datastore.AppDataStore
import com.johnpaulcas.watchly.persistence.datastore.AppDataStoreImpl
import com.johnpaulcas.watchly.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    /** ===================== DATABASE/DAO DEPENDENCY ==================== */
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
    ) = Room.databaseBuilder(app, TrackDatabase::class.java, "track_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTrackDao(database: TrackDatabase) = database.trackDao()

    /** ===================== DataStore DEPENDENCY ==================== */
    @Singleton
    @Provides
    fun provideDataStore(
        app: Application
    ): DataStore<Preferences> = app.createDataStore(name = "settings")

    @Singleton
    @Provides
    fun provideAppDataStore(
        dataStore: DataStore<Preferences>
    ): AppDataStore = AppDataStoreImpl(dataStore)

    /** ===================== RETROFIT DEPENDENCY ==================== */
    @Provides
    fun provideBaseUrl() = AppConstant.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .connectTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .build()
    }

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