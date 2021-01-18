package com.johnpaulcas.watchly.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by johnpaulcas on 18/01/2021.
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        init()
    }

    /**
     * Set activity layout
     */
    abstract fun getLayoutResourceId(): Int

    /**
     * Initialize views/components
     */
    abstract fun init()

}