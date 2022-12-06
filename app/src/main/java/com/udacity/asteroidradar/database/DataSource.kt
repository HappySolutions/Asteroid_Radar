package com.udacity.asteroidradar.database

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun refreshData()
    //can I use suspend with flow
    suspend fun getAllAsteroids(): Flow<List<Asteroid>>
    suspend fun getNasaPic(): PictureOfDay?
    suspend fun onWeekAsteroidsClicked(): Flow<List<Asteroid>>
    suspend fun onTodayAsteroidsClicked(): Flow<List<Asteroid>>
    suspend fun onSavedAsteroidsClicked(): Flow<List<Asteroid>>
}