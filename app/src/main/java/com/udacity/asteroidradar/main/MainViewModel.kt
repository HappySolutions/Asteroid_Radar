package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidsDatabaseDao
import com.udacity.asteroidradar.database.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainViewModel(private val mainRepo: DataSource,
    application: Application) : ViewModel() {

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>> get()= _asteroidList

    init{
        viewModelScope.launch {
            try {
                mainRepo.refreshData()
                getAsteroids()
            } catch (e: Exception) {
                Log.e("MainViewModel", e.message.toString())
                Toast.makeText(application, "Failure: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getAsteroids() {
        viewModelScope.launch (Dispatchers.IO){
            mainRepo.getAllAsteroids().collect{
                _asteroidList.value = it
            }
        }
    }
    }

