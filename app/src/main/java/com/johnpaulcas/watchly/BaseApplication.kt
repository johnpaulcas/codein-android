package com.johnpaulcas.watchly

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}