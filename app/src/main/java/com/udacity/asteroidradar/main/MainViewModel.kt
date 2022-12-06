package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.getSeventhDay
import com.udacity.asteroidradar.api.getToday
import com.udacity.asteroidradar.database.AsteroidsDatabaseDao
import com.udacity.asteroidradar.database.DataSource
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainViewModel(private val mainRepo: DataSource,
    application: Application) : ViewModel() {

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>> get() = _asteroidList

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    init {
        onViewWeekAsteroidsClicked()
        onViewTodayAsteroidsClicked()
        onViewSavedAsteroidsClicked()
        viewModelScope.launch {
            try {
                //refreshPictureOfDay()
                mainRepo.refreshData()
            } catch (e: Exception) {
                Log.e("MainViewModel", e.message.toString())
                Toast.makeText(application, "Failure: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
        getAsteroids()

    }

    private suspend fun refreshPictureOfDay() {
        _pictureOfDay.value = mainRepo.getNasaPic()
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            mainRepo.getAllAsteroids().collect {
                _asteroidList.value = it
            }
        }
    }

    fun onViewWeekAsteroidsClicked() {
        viewModelScope.launch(Dispatchers.Main) {
            mainRepo.onWeekAsteroidsClicked()
                .collect { asteroids ->
                    _asteroidList.value = asteroids
                }
        }
    }

    fun onViewTodayAsteroidsClicked() {
        viewModelScope.launch {
            mainRepo.onTodayAsteroidsClicked()
                .collect { asteroids ->
                    _asteroidList.value = asteroids
                }
        }
    }

    fun onViewSavedAsteroidsClicked() {
        viewModelScope.launch {
            mainRepo.onSavedAsteroidsClicked()
                .collect { asteroids ->
                    _asteroidList.value = asteroids
                }
        }


    }

}