package com.johnpaulcas.watchly.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.gson.Gson
import com.johnpaulcas.watchly.persistence.database.Track
import com.johnpaulcas.watchly.persistence.datastore.AppDataStore
import com.johnpaulcas.watchly.utils.AppConstant
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch

/**
 * Created by johnpaulcas on 20/01/2021.
 */
@FragmentScoped
class TrackDetailViewModel @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle,
    private val dataStore: AppDataStore
): ViewModel() {

//    val _track = state.get<Track>("track")
    private val _track = MutableLiveData<Track>()

    val track: LiveData<Track>
        get() = _track

    init {
        _track.postValue(state.get<Track>("track"))
    }

    // save the cache in datastore
    fun cachedTrackData(track: Track) = viewModelScope.launch {
        val trackCache = Gson().toJson(track)
        dataStore.saveString(AppConstant.DataStore.CACHED_DATA, trackCache)
    }

    // retrieve track from datastore cache
    fun loadLastKnowCacheData() = viewModelScope.launch {
        val cacheData = dataStore.readString(AppConstant.DataStore.CACHED_DATA)
        val track = Gson().fromJson(cacheData, Track::class.java)
        _track.postValue(track)
    }

}