package com.johnpaulcas.watchly.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by johnpaulcas on 18/01/2021.
 *
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onBindLayoutResource())
        init()
    }

    /**
     * set activity layout
     */
    abstract fun onBindLayoutResource(): View

    /**
     * method to initialize view/components
     */
    abstract fun init()

}