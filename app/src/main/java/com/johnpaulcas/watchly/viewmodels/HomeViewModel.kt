package com.johnpaulcas.watchly.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnpaulcas.watchly.api.reponse.TrackResponse
import com.johnpaulcas.watchly.repositories.home.HomeRepository
import com.johnpaulcas.watchly.utils.Resource
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@FragmentScoped
class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _response = MutableLiveData<Resource<TrackResponse>>()

    val response: LiveData<Resource<TrackResponse>>
        get() = _response

    fun getTracks() = viewModelScope.launch {
        _response.postValue(Resource.loading(null))
        homeRepository.getTracks().let { response ->
            if (response.isSuccessful) {
                Log.d("HomeViewModel", "getTracks: ${response.body().toString()}")
                _response.postValue(Resource.success(response.body()))
            } else {
                _response.postValue(Resource.error(response.errorBody().toString(), null))
            }
        }
    }

}