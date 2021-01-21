package com.johnpaulcas.watchly.utils

import java.lang.Exception
import java.net.InetAddress

/**
 * Created by johnpaulcas on 21/01/2021.
 */
object NetworkUtil {

    /**
     * Check internet availability, It's possible that device is connected to network
     * but no internet
     */
    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddress = InetAddress.getByName("google.com")
            !ipAddress.equals("")
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}