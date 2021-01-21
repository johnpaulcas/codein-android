package com.johnpaulcas.watchly.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnpaulcas.watchly.persistence.database.Track
import com.johnpaulcas.watchly.persistence.database.TrackDao
import com.johnpaulcas.watchly.repositories.home.HomeRepository
import com.johnpaulcas.watchly.utils.Resource
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by johnpaulcas on 18/01/2021.
 */
@FragmentScoped
class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository,
    private val trackDao: TrackDao
): ViewModel() {

    private val _response = MutableLiveData<Resource<List<Unit>>>()

    val response: LiveData<Resource<List<Unit>>>
        get() = _response

    val tracks: LiveData<List<Track>>
        get() = trackDao.getAllTracks()


    fun requestData() = viewModelScope.launch {
        _response.postValue(Resource.loading(null))
        try {
            homeRepository.getTracks().let { response ->
                if (response.isSuccessful) {
                    val tracks = response.body()?.results
                    tracks?.let {
                        trackDao.deleteAll()
                        trackDao.insertAll(it)
                    }
                    _response.postValue(Resource.success(null))

                } else {
                    _response.postValue(Resource.error(response.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _response.postValue(Resource.error("Something went wrong :(", null))
        }
    }

}