package com.johnpaulcas.watchly.ui

import android.view.View
import com.johnpaulcas.watchly.R
import com.johnpaulcas.watchly.base.BaseActivity
import com.johnpaulcas.watchly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onBindLayoutResource(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun init() {
    }

}