package com.johnpaulcas.watchly.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.johnpaulcas.watchly.repositories.home.HomeRepository
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@FragmentScoped
class HomeViewModel @ViewModelInject constructor(
    val homeRepository: HomeRepository
): ViewModel() {



}