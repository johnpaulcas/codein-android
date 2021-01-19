package com.johnpaulcas.watchly.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnpaulcas.watchly.persistence.datastore.AppDataStore
import com.johnpaulcas.watchly.utils.AppConstant
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * Created by johnpaulcas on 19/01/2021.
 */
@ActivityScoped
class MainViewModel @ViewModelInject  constructor(
    private val dataStore: AppDataStore
): ViewModel() {

    private val _lastVisitedDate = MutableLiveData<String>()
    private val _lastKnownScreen = MutableLiveData<String>()

    val lastVisitedDate: LiveData<String>
        get() = _lastVisitedDate

    val lastKnownScreen: LiveData<String>
        get() = _lastKnownScreen

    /**
     * Get last visited date
     */
    fun getLastVisitedDate() = viewModelScope.launch {
        val lastDateVisited = dataStore.readString(AppConstant.DataStore.DATE_VISITED)
        _lastVisitedDate.postValue(lastDateVisited)
        dataStore.saveString(AppConstant.DataStore.DATE_VISITED, getCurrentDate())
    }

    /**
     * Save the last known screen
     */
    fun saveLastKnownScreen(screenLabel: String) = viewModelScope.launch {
        dataStore.saveString(AppConstant.DataStore.LAST_KNOWN_SCREEN, screenLabel)
    }

    fun loadLastKnownScreen() = viewModelScope.launch {
        val lastKnownScreen = dataStore.readString(AppConstant.DataStore.LAST_KNOWN_SCREEN)
        if (lastKnownScreen.equals(AppConstant.Screen.TRACK_DETAILS, true)) {
            // load only last known screen if not home
            _lastKnownScreen.postValue(lastKnownScreen)
        }
    }

    /**
     * Get the current date with format hh:mm MMM dd, yyyy
     */
    private fun getCurrentDate(): String {
        val fromDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
        val date = fromDateFormat.parse(LocalDateTime.now().toString())
        val toDateFormat = SimpleDateFormat("hh:mm MMM dd, yyyy", Locale.US)
        return toDateFormat.format(date)
    }

}